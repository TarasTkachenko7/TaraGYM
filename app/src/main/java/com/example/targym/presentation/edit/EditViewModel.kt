package com.example.targym.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.R
import com.example.targym.data.util.IdGenerator
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.usecase.exercise.DeleteExerciseUseCase
import com.example.targym.domain.usecase.exercise.GetExerciseByIdUseCase
import com.example.targym.domain.usecase.exercise.SaveExerciseUseCase
import com.example.targym.domain.util.DomainResult
import com.example.targym.presentation.edit.state.EditUiAction
import com.example.targym.presentation.edit.state.EditUiState
import com.example.targym.presentation.edit.state.RenameDialogState
import com.example.targym.presentation.edit.state.RepetitionInputState
import com.example.targym.presentation.mapper.toDomain
import com.example.targym.presentation.mapper.toEditUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditViewModel(
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val saveExerciseUseCase: SaveExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    private var originalExercise: Exercise? = null

    fun initExercise(exerciseId: Long, dayId: Long, muscleGroup: MuscleGroup, defaultName: String) {
        if (_uiState.value.dayId != -1L) return

        viewModelScope.launch {
            if (exerciseId == -1L) {
                val initialRepetition = RepetitionInputState(
                    id = IdGenerator.generateId(),
                    weight = "",
                    quantity = ""
                )

                val newExercise = Exercise(
                    id = -1L,
                    workoutDayId = dayId,
                    muscleGroup = muscleGroup,
                    name = defaultName,
                    repetitions = emptyList(),
                    note = null
                )

                originalExercise = newExercise

                _uiState.update {
                    newExercise.toEditUiState(isNewExercise = true).copy(
                        repetitions = persistentListOf(initialRepetition)
                    )
                }
                recalculateSaveEnabled()
            } else {
                val exercise = getExerciseByIdUseCase(exerciseId).firstOrNull()
                if (exercise != null) {
                    originalExercise = exercise
                    _uiState.update { exercise.toEditUiState(isNewExercise = false) }
                    recalculateSaveEnabled()
                } else {
                    _uiState.update {
                        it.copy(
                            dayId = dayId,
                            muscleGroup = muscleGroup,
                            errorMessage = R.string.error_not_found,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName) }
        recalculateSaveEnabled()
    }

    fun onNoteChange(newNote: String) {
        _uiState.update { it.copy(note = newNote) }
        recalculateSaveEnabled()
    }

    fun onRepetitionChange(repId: Long, weightStr: String, repsStr: String) {
        _uiState.update { state ->
            val updated = state.repetitions.map { rep ->
                if (rep.id == repId) {
                    val cleanWeight = weightStr.replace(',', '.')
                    val isValidWeight = cleanWeight.isEmpty() || cleanWeight.toDoubleOrNull() != null
                    val finalWeight = if (isValidWeight) cleanWeight else rep.weight
                    val cleanReps = repsStr.filter { it.isDigit() }

                    rep.copy(weight = finalWeight, quantity = cleanReps)
                } else {
                    rep
                }
            }.toImmutableList()
            state.copy(repetitions = updated)
        }
        recalculateSaveEnabled()
    }

    fun addRepetition() {
        _uiState.update { state ->
            val newRepetition = RepetitionInputState(
                id = IdGenerator.generateId(),
                weight = "",
                quantity = ""
            )
            state.copy(
                repetitions = (state.repetitions + newRepetition).toImmutableList(),
                isSaveEnabled = true
            )
        }
    }

    fun removeRepetition(repId: Long) {
        _uiState.update { state ->
            state.copy(
                repetitions = state.repetitions.filterNot { it.id == repId }.toImmutableList()
            )
        }
        recalculateSaveEnabled()
    }

    private fun recalculateSaveEnabled() {
        val state = _uiState.value
        val isNameValid = state.name.isNotBlank()
        val hasRepetitions = state.repetitions.isNotEmpty()
        val hasChanges = hasUnsavedChanges()
        val isEnabled = isNameValid && hasRepetitions && hasChanges

        _uiState.update { it.copy(isSaveEnabled = isEnabled) }
    }

    fun saveExercise() {
        val state = _uiState.value
        if (!state.isSaveEnabled) return

        val fallbackGroup = state.muscleGroup ?: MuscleGroup.CHEST
        val exerciseToSave = state.toDomain(fallbackGroup)

        viewModelScope.launch {
            when (saveExerciseUseCase(exerciseToSave)) {
                is DomainResult.Success -> _uiState.update { it.copy(isSaved = true) }
                is DomainResult.Error -> {
                    _uiState.update { it.copy(errorMessage = R.string.error_empty_name) }
                }
            }
        }
    }

    fun hasUnsavedChanges(): Boolean {
        val current = _uiState.value
        val original = originalExercise ?: return current.name.isNotBlank() || current.repetitions.isNotEmpty()

        val fallbackGroup = current.muscleGroup ?: original.muscleGroup
        val currentConverted = current.toDomain(fallbackGroup)

        return currentConverted != original
    }

    fun onBackRequested(onConfirmNavigate: () -> Unit) {
        if (hasUnsavedChanges()) {
            _uiState.update { it.copy(showExitConfirmationDialog = true) }
        } else {
            onConfirmNavigate()
        }
    }

    fun dismissExitDialog() {
        _uiState.update { it.copy(showExitConfirmationDialog = false) }
    }

    fun confirmExitWithoutSaving(onConfirmNavigate: () -> Unit) {
        dismissExitDialog()
        onConfirmNavigate()
    }

    fun openDeleteConfirmationDialog() {
        _uiState.update {
            it.copy(
                isMenuExpanded = false,
                isDeleteConfirmationOpen = true
            )
        }
    }

    fun closeDeleteConfirmationDialog() {
        _uiState.update { it.copy(isDeleteConfirmationOpen = false) }
    }

    fun confirmDeleteExercise() {
        val state = _uiState.value
        closeDeleteConfirmationDialog()

        viewModelScope.launch {
            if (state.exerciseId != -1L) {
                deleteExerciseUseCase(state.exerciseId)
            }
            _uiState.update { it.copy(isSaved = true) }
        }
    }

    fun toggleMenu(isOpen: Boolean) {
        _uiState.update { it.copy(isMenuExpanded = isOpen) }
    }

    fun openRenameDialog() {
        _uiState.update {
            it.copy(
                isMenuExpanded = false,
                renameDialog = RenameDialogState(
                    isOpen = true,
                    tempNameInput = it.name
                )
            )
        }
    }

    fun closeRenameDialog() {
        _uiState.update {
            it.copy(renameDialog = RenameDialogState())
        }
    }

    fun onTempNameChanged(newName: String) {
        _uiState.update { state ->
            state.copy(
                renameDialog = state.renameDialog.copy(tempNameInput = newName)
            )
        }
    }

    fun confirmRename() {
        val newName = _uiState.value.renameDialog.tempNameInput.trim()
        if (newName.isNotBlank()) {
            _uiState.update {
                it.copy(
                    name = newName,
                    renameDialog = RenameDialogState()
                )
            }
            recalculateSaveEnabled()
        }
    }

    fun onAction(action: EditUiAction, onConfirmNavigate: (() -> Unit)? = null) {
        when (action) {
            is EditUiAction.NavigateBack -> onConfirmNavigate?.let { onBackRequested(it) }
            is EditUiAction.ToggleMenu -> toggleMenu(action.isOpen)
            is EditUiAction.NameChanged -> onNameChange(action.name)
            is EditUiAction.NoteChanged -> onNoteChange(action.note)
            is EditUiAction.RepetitionChanged -> onRepetitionChange(action.repId, action.weight, action.reps)
            is EditUiAction.AddRepetition -> addRepetition()
            is EditUiAction.RemoveRepetition -> removeRepetition(action.repId)
            is EditUiAction.OpenRenameDialog -> openRenameDialog()
            is EditUiAction.CloseRenameDialog -> closeRenameDialog()
            is EditUiAction.TempNameChanged -> onTempNameChanged(action.name)
            is EditUiAction.ConfirmRename -> confirmRename()
            is EditUiAction.OpenDeleteConfirmation -> openDeleteConfirmationDialog()
            is EditUiAction.CloseDeleteConfirmation -> closeDeleteConfirmationDialog()
            is EditUiAction.ConfirmDelete -> confirmDeleteExercise()
            is EditUiAction.DismissExitDialog -> dismissExitDialog()
            is EditUiAction.SaveExercise -> saveExercise()
        }
    }

}

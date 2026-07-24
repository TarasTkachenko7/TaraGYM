package com.example.targym.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.targym.R
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update

class EditViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    private var originalExercise: Exercise? = null

    fun initExercise(exerciseId: Long, dayId: Long, muscleGroup: MuscleGroup, defaultName: String) {
        if (_uiState.value.dayId != -1L) return

        viewModelScope.launch {
            if (exerciseId == -1L) {
                val initialRepetition = RepetitionInputState(
                    id = System.currentTimeMillis(),
                    weight = "",
                    quantity = ""
                )

                originalExercise = Exercise(
                    id = -1L,
                    workoutDayId = dayId,
                    muscleGroup = muscleGroup,
                    name = defaultName,
                    repetitions = emptyList(),
                    note = null
                )

                _uiState.update {
                    it.copy(
                        exerciseId = -1L,
                        dayId = dayId,
                        muscleGroup = muscleGroup,
                        name = defaultName,
                        repetitions = listOf(initialRepetition),
                        isNewExercise = true,
                        isLoading = false
                    )
                }
                recalculateSaveEnabled()

            } else {
                val exercises = repository.getExercisesByWorkoutDay(dayId).firstOrNull() ?: emptyList()
                val exercise = exercises.find { it.id == exerciseId }

                if (exercise != null) {
                    originalExercise = exercise
                    _uiState.update {
                        EditUiState(
                            exerciseId = exercise.id,
                            dayId = exercise.workoutDayId,
                            name = exercise.name,
                            note = exercise.note ?: "",
                            muscleGroup = exercise.muscleGroup,
                            repetitions = exercise.repetitions.map { rep ->
                                RepetitionInputState(
                                    id = rep.id,
                                    weight = if (rep.weight == 0.0) "" else if (rep.weight % 1 == 0.0) rep.weight.toInt().toString() else rep.weight.toString(),
                                    quantity = if (rep.quantity == 0) "" else rep.quantity.toString()
                                )
                            },
                            isNewExercise = false,
                            isLoading = false
                        )
                    }
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
        _uiState.update { it.copy(name = newName, isNameError = false) }
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
                    val cleanWeight = weightStr.replace(',', '.').filter { it.isDigit() || it == '.' }
                    val cleanReps = repsStr.filter { it.isDigit() }
                    rep.copy(weight = cleanWeight, quantity = cleanReps)
                } else {
                    rep
                }
            }
            state.copy(repetitions = updated)
        }
        recalculateSaveEnabled()
    }

    fun addRepetition() {
        _uiState.update { state ->
            val newRepetition = RepetitionInputState(
                id = System.currentTimeMillis(),
                weight = "",
                quantity = ""
            )
            state.copy(repetitions = state.repetitions + newRepetition, isSaveEnabled = true)
        }
    }

    fun removeRepetition(repId: Long) {
        _uiState.update { state ->
            state.copy(repetitions = state.repetitions.filterNot { it.id == repId })
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

        val domainRepetitions = state.repetitions.map { rep ->
            val weight = rep.weight.toDoubleOrNull() ?: 0.0
            val quantity = rep.quantity.toIntOrNull() ?: 0
            Repetition(
                id = rep.id,
                exerciseId = state.exerciseId,
                weight = weight,
                quantity = quantity,
                isDone = false
            )
        }

        viewModelScope.launch {
            val exerciseToSave = Exercise(
                id = if (state.exerciseId == -1L) System.currentTimeMillis() else state.exerciseId,
                workoutDayId = state.dayId,
                muscleGroup = state.muscleGroup!!,
                name = state.name.trim(),
                repetitions = domainRepetitions,
                note = state.note.trim().ifBlank { null }
            )

            repository.saveExercise(exerciseToSave)
            _uiState.update { it.copy(isSaved = true) }
        }
    }

    fun hasUnsavedChanges(): Boolean {
        val current = _uiState.value
        val original = originalExercise ?: return current.name.isNotBlank() || current.repetitions.isNotEmpty()

        val currentDomainRepetitions = current.repetitions.map { rep ->
            val weight = rep.weight.toDoubleOrNull() ?: 0.0
            val quantity = rep.quantity.toIntOrNull() ?: 0
            Repetition(id = rep.id, exerciseId = current.exerciseId, weight = weight, quantity = quantity, isDone = false)
        }

        val currentExerciseConverted = Exercise(
            id = current.exerciseId,
            workoutDayId = current.dayId,
            muscleGroup = current.muscleGroup ?: original.muscleGroup,
            name = current.name.trim(),
            repetitions = currentDomainRepetitions,
            note = current.note.trim().ifBlank { null }
        )

        return currentExerciseConverted != original
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
                repository.deleteExercise(state.exerciseId)
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
                isRenameDialogOpen = true,
                tempNameInput = it.name
            )
        }
    }

    fun closeRenameDialog() {
        _uiState.update { it.copy(isRenameDialogOpen = false) }
    }

    fun onTempNameChanged(newName: String) {
        _uiState.update { it.copy(tempNameInput = newName) }
    }

    fun confirmRename() {
        val newName = _uiState.value.tempNameInput.trim()
        if (newName.isNotBlank()) {
            _uiState.update {
                it.copy(
                    name = newName,
                    isRenameDialogOpen = false,
                    isNameError = false
                )
            }
            recalculateSaveEnabled()
        }
    }

    fun deleteExercise() {
        val state = _uiState.value
        toggleMenu(false)
        viewModelScope.launch {
            if (state.exerciseId != -1L) {
                repository.deleteExercise(state.exerciseId)
            }
            _uiState.update { it.copy(isSaved = true) }
        }
    }

}

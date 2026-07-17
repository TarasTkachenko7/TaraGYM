package com.example.targym.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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

    fun initExercise(exerciseId: Long, dayId: Long, muscleGroup: MuscleGroup) {
        if (_uiState.value.dayId != -1L) return

        viewModelScope.launch {
            if (exerciseId == -1L) {
                originalExercise = Exercise(
                    id = -1L,
                    workoutDayId = dayId,
                    muscleGroup = muscleGroup,
                    name = "",
                    repetitions = emptyList(),
                    note = null
                )
                _uiState.update {
                    it.copy(dayId = dayId, muscleGroup = muscleGroup, isLoading = false)
                }
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
                                    weight = if (rep.weight % 1 == 0.0) rep.weight.toInt().toString() else rep.weight.toString(),
                                    quantity = rep.quantity.toString()
                                )
                            },
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(dayId = dayId, muscleGroup = muscleGroup, errorMessage = R.string.error_not_found, isLoading = false)
                    }
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName, isNameError = false) }
    }

    fun onNoteChange(newNote: String) {
        _uiState.update { it.copy(note = newNote) }
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
    }

    fun addRepetition() {
        _uiState.update { state ->
            val newRepetition = RepetitionInputState(
                id = System.currentTimeMillis(),
                weight = "",
                quantity = ""
            )
            state.copy(repetitions = state.repetitions + newRepetition)
        }
    }

    fun removeRepetition(repId: Long) {
        _uiState.update { state ->
            state.copy(repetitions = state.repetitions.filterNot { it.id == repId })
        }
    }

    fun saveExercise() {
        val state = _uiState.value

        val isNameInvalid = state.name.isBlank()
        val isMuscleGroupInvalid = state.muscleGroup == null

        if (isNameInvalid || isMuscleGroupInvalid) {
            _uiState.update {
                it.copy(
                    isNameError = isNameInvalid,
                    isMuscleGroupError = isMuscleGroupInvalid,
                    errorMessage = if (isNameInvalid) R.string.error_empty_name else R.string.error_empty_muscle_group
                )
            }
            return
        }

        val domainRepetitions = state.repetitions.mapNotNull { rep ->
            val weight = rep.weight.toDoubleOrNull()
            val quantity = rep.quantity.toIntOrNull()
            if (weight != null && quantity != null) {
                Repetition(id = rep.id, exerciseId = state.exerciseId, weight = weight, quantity = quantity, isDone = false)
            } else {
                null
            }
        }

        if (domainRepetitions.isEmpty()) {
            _uiState.update { it.copy(errorMessage = R.string.error_empty_reps) }
            return
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
        val original = originalExercise ?: return false
        val current = _uiState.value

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

}

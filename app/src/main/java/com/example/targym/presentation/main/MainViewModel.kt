package com.example.targym.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {
    private val _selectedDayId = MutableStateFlow<Long?>(null)

    val screenState: StateFlow<MainScreenState> = combine(
        repository.getWorkoutDays(),
        _selectedDayId
    ) { days, selectedId ->
        days to selectedId
    }.flatMapLatest { (days, selectedId) ->
        if (days.isEmpty()) {
            flowOf(MainScreenState.Empty)
        } else {
            val activeDayId = selectedId?.takeIf { id -> days.any { it.id == id } }
                ?: days.first().id

            repository.getExercisesByWorkoutDay(activeDayId).map { domainExercises ->

                val hasActiveWorkout = domainExercises.any { exercise ->
                    exercise.repetitions.any { it.isDone }
                }

                val uiExercises = domainExercises.map { exercise ->
                    ExerciseUiModel(
                        id = exercise.id,
                        name = exercise.name,
                        note = exercise.note,
                        muscleGroup = exercise.muscleGroup,
                        repetitions = exercise.repetitions.mapIndexed { index, rep ->
                            RepetitionUiModel(
                                id = rep.id,
                                indexText = "${index + 1}.",
                                weightText = formatWeight(rep.weight),
                                quantityText = rep.quantity.toString(),
                                isDone = rep.isDone
                            )
                        }
                    )
                }

                val grouped = uiExercises.groupBy { it.muscleGroup }

                MainScreenState.Success(
                    uiState = MainUiState(
                        workoutDays = days,
                        selectedDayId = activeDayId,
                        groupedExercises = grouped,
                        isExercisesLoading = false,
                        hasActiveWorkout = hasActiveWorkout
                    )
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainScreenState.Loading
    )

    private fun formatWeight(weight: Double): String {
        return if (weight % 1 == 0.0) {
            weight.toInt().toString()
        } else {
            weight.toString()
        }
    }

    fun selectDay(dayId: Long) {
        _selectedDayId.value = dayId
    }

    fun toggleRepetitionCheck(exerciseId: Long, repetitionId: Long) {
        viewModelScope.launch {
            repository.toggleRepetitionDone(exerciseId, repetitionId)
        }
    }

    fun finishCurrentWorkout(workoutDayId: Long) {
        viewModelScope.launch {
            repository.resetAllDoneFlags(workoutDayId)
        }
    }

}
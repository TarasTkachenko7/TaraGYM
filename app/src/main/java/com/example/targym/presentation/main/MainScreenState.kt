package com.example.targym.presentation.main

import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.WorkoutDay

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data object Empty : MainScreenState
    data class Success(val uiState: MainUiState) : MainScreenState
}

@Immutable
data class MainUiState(
    val workoutDays: List<WorkoutDay>,
    val selectedDayId: Long,
    val groupedExercises: Map<MuscleGroup, List<ExerciseUiModel>>,
    val isExercisesLoading: Boolean,
    val hasActiveWorkout: Boolean,
    val isMuscleBottomSheetOpen: Boolean = false,
    val availableMuscleGroups: List<MuscleGroup> = emptyList(),
    val activeMuscleMenuGroup: MuscleGroup? = null,
    val muscleGroupPendingDeletion: MuscleGroup? = null
)

@Immutable
data class RepetitionUiModel(
    val id: Long,
    val indexText: String,
    val weightText: String,
    val quantityText: String,
    val isDone: Boolean
)

@Immutable
data class ExerciseUiModel(
    val id: Long,
    val name: String,
    val note: String?,
    val muscleGroup: MuscleGroup,
    val repetitions: List<RepetitionUiModel>
)
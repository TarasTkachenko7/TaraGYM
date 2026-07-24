package com.example.targym.presentation.main.state

import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.model.ExerciseUiModel
import com.example.targym.presentation.model.WorkoutDayUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class MainUiState(
    val workoutDays: ImmutableList<WorkoutDayUiModel>,
    val selectedDayId: Long,
    val groupedExercises: ImmutableMap<MuscleGroup, ImmutableList<ExerciseUiModel>>,
    val hasActiveWorkout: Boolean,
    val isMuscleBottomSheetOpen: Boolean = false,
    val availableMuscleGroups: ImmutableList<MuscleGroup> = persistentListOf(),
    val activeMuscleMenuGroup: MuscleGroup? = null,
    val muscleGroupPendingDeletion: MuscleGroup? = null
)
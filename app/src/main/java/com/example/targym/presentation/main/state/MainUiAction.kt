package com.example.targym.presentation.main.state

import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup

@Immutable
sealed interface MainUiAction {
    // === 1. Взаимодействие с днями тренировок ===
    data class SelectDay(val dayId: Long) : MainUiAction
    data object OpenManageDays : MainUiAction

    // === 2. Тренировочный процесс ===
    data class ToggleRepetition(val exerciseId: Long, val repetitionId: Long) : MainUiAction
    data class FinishWorkout(val workoutDayId: Long) : MainUiAction

    // === 3. Управление группами мышц ===
    data object OpenMuscleBottomSheet : MainUiAction
    data object CloseMuscleBottomSheet : MainUiAction
    data class AddMuscleGroup(val muscleGroup: MuscleGroup) : MainUiAction
    data class ToggleMuscleMenu(val muscleGroup: MuscleGroup, val isOpen: Boolean) : MainUiAction
    data class RequestDeleteMuscleGroup(val muscleGroup: MuscleGroup) : MainUiAction
    data object ConfirmDeleteMuscleGroup : MainUiAction
    data object DismissDeleteMuscleGroupDialog : MainUiAction

    // === 4. Управление упражнениями и навигация ===
    data class AddExercise(val muscleGroup: MuscleGroup) : MainUiAction
    data class OpenEditExercise(val exerciseId: Long) : MainUiAction
    data class OpenVideo(val exerciseId: Long) : MainUiAction
}
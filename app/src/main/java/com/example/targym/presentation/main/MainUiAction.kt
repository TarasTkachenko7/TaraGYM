package com.example.targym.presentation.main

import com.example.targym.domain.model.MuscleGroup

sealed interface MainUiAction {

    data class SelectDay(val dayId: Long) : MainUiAction
    data class ToggleRepetition(val exerciseId: Long, val repetitionId: Long) : MainUiAction
    data class DeleteMuscleGroup(val muscleGroup: MuscleGroup) : MainUiAction
    data class FinishWorkout(val workoutDayId: Long) : MainUiAction
    data object OpenMuscleBottomSheet : MainUiAction
    data object CloseMuscleBottomSheet : MainUiAction
    data class AddMuscleGroup(val muscleGroup: MuscleGroup) : MainUiAction
    data class ToggleMuscleMenu(val muscleGroup: MuscleGroup, val isOpen: Boolean) : MainUiAction

    data object OpenManageDays : MainUiAction
    data class AddExercise(val muscleGroup: MuscleGroup) : MainUiAction
    data class OpenVideo(val exerciseId: Long) : MainUiAction
    data class OpenEditExercise(val exerciseId: Long) : MainUiAction

}
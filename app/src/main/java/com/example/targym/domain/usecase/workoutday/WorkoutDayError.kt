package com.example.targym.domain.usecase.workoutday

sealed interface WorkoutDayNameError {
    data object EmptyName : WorkoutDayNameError
    data object DuplicateName : WorkoutDayNameError
}
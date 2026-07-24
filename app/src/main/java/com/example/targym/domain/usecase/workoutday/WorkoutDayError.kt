package com.example.targym.domain.usecase.workoutday

sealed interface AddWorkoutDayError {
    data object EmptyName : AddWorkoutDayError
    data object DuplicateName : AddWorkoutDayError
}

sealed interface UpdateWorkoutNameError {
    data object EmptyName : UpdateWorkoutNameError
    data object DuplicateName : UpdateWorkoutNameError
}
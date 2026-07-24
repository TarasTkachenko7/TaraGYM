package com.example.targym.domain.usecase.exercise

sealed interface SaveExerciseError {
    data object EmptyName : SaveExerciseError
}
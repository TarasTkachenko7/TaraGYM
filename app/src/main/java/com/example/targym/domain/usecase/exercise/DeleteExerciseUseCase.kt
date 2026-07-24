package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.repository.ExerciseRepository

class DeleteExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Long) {
        repository.deleteExercise(exerciseId)
    }
}
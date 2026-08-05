package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.firstOrNull

class UpdateExerciseMediaUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Long, mediaUri: String?) {
        val exercise = repository.getExerciseById(exerciseId).firstOrNull() ?: return
        val updatedExercise = exercise.copy(mediaUri = mediaUri)
        repository.saveExercise(updatedExercise)
    }
}
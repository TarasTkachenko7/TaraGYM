package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetExerciseByIdUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(exerciseId: Long): Flow<Exercise?> =
        repository.getExerciseById(exerciseId)
}
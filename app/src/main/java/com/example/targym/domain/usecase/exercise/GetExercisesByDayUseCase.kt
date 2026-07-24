package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetExercisesByDayUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(workoutDayId: Long): Flow<List<Exercise>> {
        return repository.getExercisesByWorkoutDay(workoutDayId)
    }
}
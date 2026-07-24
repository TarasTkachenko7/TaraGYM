package com.example.targym.domain.usecase.workout

import com.example.targym.domain.repository.ExerciseRepository

class FinishWorkoutUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(workoutDayId: Long) {
        repository.resetAllDoneFlags(workoutDayId)
    }
}
package com.example.targym.domain.usecase.workoutday

import com.example.targym.domain.repository.WorkoutDayRepository

class DeleteWorkoutDayUseCase(
    private val repository: WorkoutDayRepository
) {
    suspend operator fun invoke(workoutDayId: Long) {
        repository.deleteWorkoutDay(workoutDayId)
    }
}
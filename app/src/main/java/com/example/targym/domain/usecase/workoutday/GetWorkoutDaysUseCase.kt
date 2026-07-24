package com.example.targym.domain.usecase.workoutday

import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutDaysUseCase(
    private val repository: WorkoutDayRepository
) {
    operator fun invoke(): Flow<List<WorkoutDay>> = repository.getWorkoutDays()
}
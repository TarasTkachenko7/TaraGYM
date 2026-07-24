package com.example.targym.domain.usecase.workoutday

import com.example.targym.domain.repository.ExerciseRepository
import com.example.targym.domain.repository.WorkoutDayRepository

class DeleteWorkoutDayUseCase(
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseRepository: ExerciseRepository
) {
    suspend operator fun invoke(workoutDayId: Long) {
        exerciseRepository.deleteExercisesByWorkoutDay(workoutDayId)
        workoutDayRepository.deleteWorkoutDay(workoutDayId)
    }
}
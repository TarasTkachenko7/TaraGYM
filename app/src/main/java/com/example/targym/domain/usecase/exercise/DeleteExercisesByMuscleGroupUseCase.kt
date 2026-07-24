package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.repository.ExerciseRepository

class DeleteExercisesByMuscleGroupUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(workoutDayId: Long, muscleGroup: MuscleGroup) {
        repository.deleteExercisesByMuscleGroup(workoutDayId, muscleGroup)
    }
}
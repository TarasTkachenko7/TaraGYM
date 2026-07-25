package com.example.targym.domain.usecase.workoutday

import com.example.targym.domain.repository.WorkoutDayRepository
import com.example.targym.domain.util.DomainResult
import kotlinx.coroutines.flow.first

class AddWorkoutDayUseCase(
    private val repository: WorkoutDayRepository
) {
    suspend operator fun invoke(name: String): DomainResult<Long, WorkoutDayNameError> {
        val trimmedName = name.trim()

        if (trimmedName.isBlank()) {
            return DomainResult.Error(WorkoutDayNameError.EmptyName)
        }

        val existingDays = repository.getWorkoutDays().first()
        val isDuplicate = existingDays.any { it.name.equals(trimmedName, ignoreCase = true) }

        if (isDuplicate) {
            return DomainResult.Error(WorkoutDayNameError.DuplicateName)
        }

        val id = repository.addWorkoutDay(trimmedName)
        return DomainResult.Success(id)
    }
}
package com.example.targym.domain.usecase.workoutday

import com.example.targym.domain.repository.WorkoutDayRepository
import com.example.targym.domain.util.DomainResult
import kotlinx.coroutines.flow.first

class UpdateWorkoutDayNameUseCase(
    private val repository: WorkoutDayRepository
) {
    suspend operator fun invoke(id: Long, newName: String): DomainResult<Unit, UpdateWorkoutNameError> {
        val trimmedName = newName.trim()

        if (trimmedName.isBlank()) {
            return DomainResult.Error(UpdateWorkoutNameError.EmptyName)
        }

        val existingDays = repository.getWorkoutDays().first()
        val isDuplicate = existingDays.any { it.id != id && it.name.equals(trimmedName, ignoreCase = true) }

        if (isDuplicate) {
            return DomainResult.Error(UpdateWorkoutNameError.DuplicateName)
        }

        repository.updateWorkoutDayName(id, trimmedName)
        return DomainResult.Success(Unit)
    }
}
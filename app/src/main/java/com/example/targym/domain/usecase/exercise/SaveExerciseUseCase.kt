package com.example.targym.domain.usecase.exercise

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.repository.ExerciseRepository
import com.example.targym.domain.util.DomainResult

class SaveExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise): DomainResult<Unit, SaveExerciseError> {
        val trimmedName = exercise.name.trim()
        if (trimmedName.isBlank()) {
            return DomainResult.Error(SaveExerciseError.EmptyName)
        }

        val cleanedNote = exercise.note?.trim()?.ifBlank { null }
        val cleanedExercise = exercise.copy (
            name = exercise.name.trim(),
            note = cleanedNote
        )

        repository.saveExercise(cleanedExercise)
        return DomainResult.Success(Unit)
    }
}
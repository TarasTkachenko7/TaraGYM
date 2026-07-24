package com.example.targym.domain.usecase.workout

import com.example.targym.domain.repository.ExerciseRepository

class ToggleRepetitionDoneUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseId: Long, repetitionId: Long) {
        repository.toggleRepetitionDone(exerciseId, repetitionId)
    }
}
package com.example.targym.presentation.mapper

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.presentation.edit.state.EditUiState
import com.example.targym.presentation.edit.state.RepetitionInputState
import com.example.targym.presentation.util.formatWeight
import kotlinx.collections.immutable.toImmutableList

fun Exercise.toEditUiState(isNewExercise: Boolean = false): EditUiState {
    val uiRepetitions = repetitions.map { rep ->
        RepetitionInputState(
            id = rep.id,
            weight = if (rep.weight == 0.0) "" else rep.weight.formatWeight(),
            quantity = if (rep.quantity == 0) "" else rep.quantity.toString()
        )
    }.toImmutableList()

    return EditUiState(
        exerciseId = id,
        dayId = workoutDayId,
        name = name,
        note = note ?: "",
        muscleGroup = muscleGroup,
        repetitions = uiRepetitions,
        isNewExercise = isNewExercise,
        isLoading = false
    )
}

fun EditUiState.toDomain(fallbackMuscleGroup: MuscleGroup): Exercise {
    val domainRepetitions = repetitions.mapIndexed { index, rep ->
        val weightVal = rep.weight.toDoubleOrNull() ?: 0.0
        val quantityVal = rep.quantity.toIntOrNull() ?: 0
        val numericId = rep.id ?: 0L

        Repetition(
            id = numericId,
            exerciseId = if (exerciseId == -1L) 0L else exerciseId,
            weight = weightVal,
            quantity = quantityVal,
            isDone = false
        )
    }

    return Exercise(
        id = if (exerciseId == -1L) 0L else exerciseId,
        workoutDayId = dayId,
        muscleGroup = muscleGroup ?: fallbackMuscleGroup,
        name = name.trim(),
        repetitions = domainRepetitions,
        note = note.trim().ifBlank { null }
    )
}
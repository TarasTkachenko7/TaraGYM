package com.example.targym.presentation.model

import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.MuscleGroup
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class ExerciseUiModel(
    val id: Long,
    val name: String,
    val note: String?,
    val muscleGroup: MuscleGroup,
    val repetitions: ImmutableList<RepetitionUiModel>
)
package com.example.targym.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class WorkoutDayUiModel(
    val id: Long,
    val name: String,
    val isSelected: Boolean
)
package com.example.targym.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class RepetitionUiModel(
    val id: Long,
    val indexText: String,
    val weightText: String,
    val quantityText: String,
    val isDone: Boolean
)
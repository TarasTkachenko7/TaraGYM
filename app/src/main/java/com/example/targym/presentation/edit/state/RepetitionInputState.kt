package com.example.targym.presentation.edit.state

import androidx.compose.runtime.Immutable

@Immutable
data class RepetitionInputState(
    val id: Long,
    val weight: String = "",
    val quantity: String = ""
)
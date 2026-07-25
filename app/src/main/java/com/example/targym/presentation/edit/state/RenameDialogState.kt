package com.example.targym.presentation.edit.state

import androidx.compose.runtime.Immutable

@Immutable
data class RenameDialogState(
    val isOpen: Boolean = false,
    val tempNameInput: String = ""
)
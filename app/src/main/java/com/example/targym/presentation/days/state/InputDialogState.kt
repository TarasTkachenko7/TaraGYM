package com.example.targym.presentation.days.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.targym.R

@Immutable
data class InputDialogState(
    val isOpen: Boolean = false,
    @StringRes val titleRes: Int = R.string.edit_training,
    val text: String = "",
    val targetDayId: Long? = null,
    @StringRes val errorMessageRes: Int? = null
)
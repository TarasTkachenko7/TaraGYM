package com.example.targym.presentation.gif

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
sealed interface GifScreenState {
    data object Loading : GifScreenState

    data class Success(
        val exerciseName: String,
        val mediaUri: String? = null
    ) : GifScreenState

    data class Error(@StringRes val messageRes: Int) : GifScreenState
}
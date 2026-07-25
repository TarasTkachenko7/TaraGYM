package com.example.targym.presentation.gif

import androidx.annotation.StringRes

sealed interface GifScreenState{
    data object Loading : GifScreenState
    data class Success(val exerciseName: String) : GifScreenState
    data class Error(@StringRes val messageRes: Int) : GifScreenState
}
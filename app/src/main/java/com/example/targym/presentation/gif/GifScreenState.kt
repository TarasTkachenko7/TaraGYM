package com.example.targym.presentation.gif

import com.example.targym.domain.model.Exercise

sealed interface GifScreenState{
    data object Loading : GifScreenState
    data class Success(val exercise: Exercise) : GifScreenState
    data class Error(val message: String) : GifScreenState
}
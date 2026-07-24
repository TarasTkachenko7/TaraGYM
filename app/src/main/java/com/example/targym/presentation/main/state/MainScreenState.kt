package com.example.targym.presentation.main.state

import androidx.compose.runtime.Immutable

@Immutable
sealed interface MainScreenState {
    data object Loading : MainScreenState
    data object Empty : MainScreenState
    data class Success(val uiState: MainUiState) : MainScreenState
}
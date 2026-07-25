package com.example.targym.presentation.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.R
import com.example.targym.domain.usecase.exercise.GetExerciseByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GifViewModel(
    private val exerciseId: Long,
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GifScreenState>(GifScreenState.Loading)
    val uiState: StateFlow<GifScreenState> = _uiState.asStateFlow()

    init {
        loadExercise()
    }

    private fun loadExercise() {
        viewModelScope.launch {
            _uiState.value = GifScreenState.Loading
            getExerciseByIdUseCase(exerciseId).collect { exercise ->
                if (exercise != null) {
                    _uiState.value = GifScreenState.Success(
                        exerciseName = exercise.name
                    )
                } else {
                    _uiState.value = GifScreenState.Error(
                        messageRes = R.string.error_not_found
                    )
                }
            }
        }
    }
}
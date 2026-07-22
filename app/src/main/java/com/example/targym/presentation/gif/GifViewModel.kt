package com.example.targym.presentation.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class GifViewModel(
    private val exerciseId: Long,
    private val repository: WorkoutRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<GifScreenState>(GifScreenState.Loading)
    val uiState: StateFlow<GifScreenState> = _uiState.asStateFlow()

    init {
        loadExercise()
    }

    private fun loadExercise() {
        viewModelScope.launch {
            _uiState.value = GifScreenState.Loading

            repository.getExerciseById(exerciseId).collect { exercise ->
                if (exercise != null) {
                    _uiState.value = GifScreenState.Success(exercise = exercise)
                } else {
                    _uiState.value = GifScreenState.Error("Упражнение не найдено")
                }
            }
        }
    }

}
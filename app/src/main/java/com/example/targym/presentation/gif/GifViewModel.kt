package com.example.targym.presentation.gif

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.R
import com.example.targym.domain.usecase.exercise.GetExerciseByIdUseCase
import com.example.targym.domain.usecase.exercise.UpdateExerciseMediaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GifViewModel(
    private val exerciseId: Long,
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val updateExerciseMediaUseCase: UpdateExerciseMediaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GifScreenState>(GifScreenState.Loading)
    val uiState: StateFlow<GifScreenState> = _uiState.asStateFlow()

    init {
        loadExercise()
    }

    private fun loadExercise() {
        viewModelScope.launch {
            getExerciseByIdUseCase(exerciseId).collect { exercise ->
                if (exercise != null) {
                    _uiState.value = GifScreenState.Success(
                        exerciseName = exercise.name,
                        mediaUri = exercise.mediaUri
                    )
                } else {
                    _uiState.value = GifScreenState.Error(
                        messageRes = R.string.error_not_found
                    )
                }
            }
        }
    }

    fun onMediaSelected(uri: Uri?) {
        viewModelScope.launch {
            updateExerciseMediaUseCase(exerciseId, uri?.toString())
        }
    }

    fun onMediaLoadError() {
        _uiState.value = GifScreenState.Error(
            messageRes = R.string.error_media_not_found
        )
    }
}
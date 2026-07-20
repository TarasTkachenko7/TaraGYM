package com.example.targym.presentation.days

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.targym.R

class ManageDaysViewModel(
    val repository: WorkoutRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ManageDaysScreenState())
    val uiState: StateFlow<ManageDaysScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getWorkoutDays().collect { days ->
                _uiState.update { it.copy(days = days, isLoading = false) }
            }
        }
    }

    fun openAddDayDialog() {
        _uiState.update {
            it.copy(
                isInputDialogOpen = true,
                inputDialogTitle = R.string.adding_a_training_day_lower,
                inputDialogText = "",
                targetDayId = null,
                errorMessage = null
            )
        }
    }

    fun openEditDayDialog(dayId: Long) {
        val currentDay = _uiState.value.days.find { it.id == dayId } ?: return
        _uiState.update {
            it.copy(
                isInputDialogOpen = true,
                inputDialogTitle = R.string.edit_training,
                inputDialogText = currentDay.name,
                targetDayId = dayId,
                errorMessage = null
            )
        }
    }

    fun onDialogTextChanged(newText: String) {
        _uiState.update { it.copy(inputDialogText = newText, errorMessage = null) }
    }

    fun closeInputDialog() {
        _uiState.update { it.copy(isInputDialogOpen = false, inputDialogText = "", targetDayId = null, errorMessage = null) }
    }

    fun submitDialogInput() {
        val state = _uiState.value
        val trimmedName = state.inputDialogText.trim()

        if (trimmedName.isBlank()) {
            _uiState.update { it.copy(errorMessage = R.string.error_empty_name) }
            return
        }

        val isDuplicate = state.days.any { it.id != state.targetDayId && it.name.equals(trimmedName, ignoreCase = true) }
        if (isDuplicate) {
            _uiState.update { it.copy(errorMessage = R.string.error_duplicate_day_name) }
            return
        }

        viewModelScope.launch {
            if (state.targetDayId == null) {
                repository.addWorkoutDay(trimmedName)
            } else {
                repository.updateWorkoutDayName(state.targetDayId, trimmedName)
            }
            closeInputDialog()
        }
    }

    fun deleteWorkoutDay(workoutDayId: Long) {
        viewModelScope.launch {
            repository.deleteWorkoutDay(workoutDayId)
            dismissDeleteConfirmation()
        }
    }

    fun showDeleteConfirmation(day: WorkoutDay) {
        _uiState.update { it.copy(dayPendingDeletion = day) }
    }

    fun dismissDeleteConfirmation() {
        _uiState.update { it.copy(dayPendingDeletion = null) }
    }

}
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

    fun addWorkoutDay() {
        viewModelScope.launch {
            val newDayId = repository.addWorkoutDay("")
            startEditing(newDayId)
        }
    }

    fun deleteWorkoutDay(workoutDayId: Long) {
        viewModelScope.launch {
            repository.deleteWorkoutDay(workoutDayId)
            dismissDeleteConfirmation()
        }
    }

    fun updateWorkoutDayName(dayId: Long, newName: String) {
        val trimmedName = newName.trim()

        if (trimmedName.isBlank()) {
            _uiState.update {
                it.copy(
                    editingDayId = dayId,
                    errorMessage = R.string.error_empty_name
                )
            }
            return
        }

        val isDuplicate = _uiState.value.days.any { it.id != dayId && it.name.equals(trimmedName, ignoreCase = true) }
        if (isDuplicate) {
            _uiState.update {
                it.copy(
                    editingDayId = dayId,
                    errorMessage = R.string.error_duplicate_day_name
                )
            }
            return
        }

        viewModelScope.launch {
            repository.updateWorkoutDayName(dayId, trimmedName)
            stopEditing()
        }
    }

    fun startEditing(dayId: Long) {
        _uiState.update { it.copy(editingDayId = dayId) }
    }

    fun stopEditing() {
        _uiState.update { it.copy(editingDayId = null) }
    }

    fun showDeleteConfirmation(day: WorkoutDay) {
        _uiState.update { it.copy(dayPendingDeletion = day) }
    }

    fun dismissDeleteConfirmation() {
        _uiState.update { it.copy(dayPendingDeletion = null) }
    }

}
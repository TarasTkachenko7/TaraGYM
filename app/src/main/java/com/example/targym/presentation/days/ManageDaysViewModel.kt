package com.example.targym.presentation.days

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targym.domain.model.WorkoutDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.targym.R
import com.example.targym.domain.usecase.workoutday.AddWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.DeleteWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.GetWorkoutDaysUseCase
import com.example.targym.domain.usecase.workoutday.UpdateWorkoutDayNameUseCase
import com.example.targym.domain.util.DomainResult
import com.example.targym.presentation.days.state.InputDialogState
import com.example.targym.presentation.days.state.ManageDaysScreenState
import com.example.targym.presentation.mapper.errorMessageRes
import com.example.targym.presentation.mapper.toUiModel
import com.example.targym.presentation.model.WorkoutDayUiModel
import kotlinx.collections.immutable.toImmutableList

class ManageDaysViewModel(
    private val getWorkoutDaysUseCase: GetWorkoutDaysUseCase,
    private val addWorkoutDayUseCase: AddWorkoutDayUseCase,
    private val updateWorkoutDayNameUseCase: UpdateWorkoutDayNameUseCase,
    private val deleteWorkoutDayUseCase: DeleteWorkoutDayUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ManageDaysScreenState())
    val uiState: StateFlow<ManageDaysScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getWorkoutDaysUseCase().collect { domainDays ->
                val uiDays = domainDays.map { it.toUiModel() }.toImmutableList()
                _uiState.update { it.copy(days = uiDays, isLoading = false) }
            }
        }
    }

    fun openAddDayDialog() {
        _uiState.update {
            it.copy(
                inputDialog = InputDialogState(
                    isOpen = true,
                    titleRes = R.string.adding_a_training_day_lower,
                    text = "",
                    targetDayId = null,
                    errorMessageRes = null
                )
            )
        }
    }

    fun openEditDayDialog(dayId: Long) {
        val currentDay = _uiState.value.days.find { it.id == dayId } ?: return
        _uiState.update {
            it.copy(
                inputDialog = InputDialogState(
                    isOpen = true,
                    titleRes = R.string.edit_training,
                    text = currentDay.name,
                    targetDayId = dayId,
                    errorMessageRes = null
                )
            )
        }
    }

    fun onDialogTextChanged(newText: String) {
        _uiState.update { state ->
            state.copy(
                inputDialog = state.inputDialog.copy(
                    text = newText,
                    errorMessageRes = null
                )
            )
        }
    }

    fun closeInputDialog() {
        _uiState.update { state ->
            state.copy(inputDialog = InputDialogState())
        }
    }

    fun submitDialogInput() {
        val dialogState = _uiState.value.inputDialog
        val name = dialogState.text

        viewModelScope.launch {
            val result = if (dialogState.targetDayId == null) {
                addWorkoutDayUseCase(name)
            } else {
                updateWorkoutDayNameUseCase(dialogState.targetDayId, name)
            }

            when (result) {
                is DomainResult.Success -> closeInputDialog()
                is DomainResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            inputDialog = state.inputDialog.copy(
                                errorMessageRes = result.error.errorMessageRes
                            )
                        )
                    }
                }
            }
        }
    }

    fun deleteWorkoutDay(workoutDayId: Long) {
        viewModelScope.launch {
            deleteWorkoutDayUseCase(workoutDayId)
            dismissDeleteConfirmation()
        }
    }

    fun showDeleteConfirmation(day: WorkoutDayUiModel) {
        _uiState.update { it.copy(dayPendingDeletion = day) }
    }

    fun dismissDeleteConfirmation() {
        _uiState.update { it.copy(dayPendingDeletion = null) }
    }

}
package com.example.targym.presentation.days.state

import androidx.annotation.StringRes
import com.example.targym.R
import androidx.compose.runtime.Immutable
import com.example.targym.presentation.model.WorkoutDayUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ManageDaysScreenState(
    val days: ImmutableList<WorkoutDayUiModel> = persistentListOf(),
    val dayPendingDeletion: WorkoutDayUiModel? = null,
    val isLoading: Boolean = true,
    val inputDialog: InputDialogState = InputDialogState()
)
package com.example.targym.presentation.days

import androidx.annotation.StringRes
import com.example.targym.R
import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.WorkoutDay

@Immutable
data class ManageDaysScreenState(
    val days: List<WorkoutDay> = emptyList(),
    val dayPendingDeletion: WorkoutDay? = null,
    val isLoading: Boolean = true,
    @StringRes val errorMessage: Int? = null,

    val isInputDialogOpen: Boolean = false,
    @StringRes val inputDialogTitle: Int = R.string.edit_training,
    val inputDialogText: String = "",
    val targetDayId: Long? = null
)
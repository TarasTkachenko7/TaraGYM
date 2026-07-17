package com.example.targym.presentation.days

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.targym.domain.model.WorkoutDay

@Immutable
data class ManageDaysScreenState(
    val days: List<WorkoutDay> = emptyList(),
    val editingDayId: Long? = null,
    val dayPendingDeletion: WorkoutDay? = null,
    val dayPendingCancellationId: Long? = null,
    val isLoading: Boolean = true,
    @StringRes val errorMessage: Int? = null
)
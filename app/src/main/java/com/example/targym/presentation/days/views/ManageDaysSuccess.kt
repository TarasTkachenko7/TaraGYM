package com.example.targym.presentation.days.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.targym.domain.model.WorkoutDay
import com.example.targym.presentation.days.components.ManageDaysItem
import com.example.targym.presentation.model.WorkoutDayUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ManageDaysSuccess(
    days: ImmutableList<WorkoutDayUiModel>, // Используем ImmutableList
    onStartEdit: (Long) -> Unit,
    onDeleteClick: (WorkoutDayUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        items(
            items = days,
            key = { it.id }
        ) { day ->
            ManageDaysItem(
                day = day,
                onStartEdit = { onStartEdit(day.id) },
                onDeleteClick = { onDeleteClick(day) }
            )
        }
    }
}
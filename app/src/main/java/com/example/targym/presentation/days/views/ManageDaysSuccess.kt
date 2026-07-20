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

@Composable
fun ManageDaysSuccess(
    days: List<WorkoutDay>,
    onStartEdit: (Long) -> Unit,
    onDeleteClick: (WorkoutDay) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            bottom = 96.dp
        )
    ) {
        items(
            items = days,
            key = { it.id }
        ) { day ->
            key(day.id) {
                ManageDaysItem(
                    day = day,
                    onStartEdit = { onStartEdit(day.id) },
                    onDeleteClick = { onDeleteClick(day) }
                )
            }
        }
    }
}
package com.example.targym.presentation.days

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.R
import com.example.targym.presentation.days.components.ManageDaysHeader
import com.example.targym.presentation.days.components.AddTrainingDayButton
import com.example.targym.presentation.days.views.ManageDaysEmpty
import com.example.targym.presentation.days.views.ManageDaysSuccess
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@Composable
fun ManageDaysScreen(
    viewModel: ManageDaysViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            ManageDaysHeader(onBackClick = onBackClick)

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                if (state.days.isEmpty()) {
                    ManageDaysEmpty(modifier = Modifier.fillMaxSize())
                } else {
                    ManageDaysSuccess(
                        days = state.days,
                        editingDayId = state.editingDayId,
                        onStartEdit = { dayId ->
                            viewModel.startEditing(dayId)
                        },
                        onSaveEdit = { dayId, newName ->
                            viewModel.updateWorkoutDayName(dayId, newName)
                        },
                        onCancelEdit = {
                            viewModel.stopEditing()
                        },
                        onDeleteClick = { day ->
                            viewModel.showDeleteConfirmation(day)
                        },
                        modifier = Modifier.padding(bottom = 100.dp)
                    )
                }
            }
        }

        AddTrainingDayButton(
            onClick = { viewModel.addWorkoutDay() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 24.dp, end = 24.dp)
        )
    }

    state.dayPendingDeletion?.let { day ->
        AlertDialog(
            onDismissRequest = { viewModel.dismissDeleteConfirmation() },
            containerColor = Second,
            title = {
                Text(
                    text = stringResource(R.string.delete_day_title),
                    color = FirstText,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.delete_day_confirm, day.name),
                    color = SecondText,
                    fontFamily = InterFont
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { viewModel.deleteWorkoutDay(day.id) }
                ) {
                    Text(
                        text = stringResource(R.string.delete),
                        color = Accent,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.dismissDeleteConfirmation() }) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = SecondText
                    )
                }
            }
        )
    }
}
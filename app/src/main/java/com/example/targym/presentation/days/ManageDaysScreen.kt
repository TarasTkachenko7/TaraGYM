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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.R
import com.example.targym.presentation.days.components.AddTrainingDayButton
import com.example.targym.presentation.days.components.ManageDaysHeader
import com.example.targym.presentation.days.views.ManageDaysEmpty
import com.example.targym.presentation.days.views.ManageDaysSuccess
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.DialogBoxTextStyle
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.Garbage
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

            if (state.days.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    ManageDaysSuccess(
                        days = state.days,
                        onStartEdit = { dayId -> viewModel.openEditDayDialog(dayId) },
                        onDeleteClick = { day -> viewModel.showDeleteConfirmation(day) },
                        modifier = Modifier.padding(bottom = 100.dp)
                    )
                }
            }
        }

        if (state.days.isEmpty()) {
            ManageDaysEmpty(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        AddTrainingDayButton(
            onClick = { viewModel.openAddDayDialog() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
        )
    }

    if (state.isInputDialogOpen) {
        InputDialog(
            title = stringResource(state.inputDialogTitle),
            text = state.inputDialogText,
            onTextChanged = { viewModel.onDialogTextChanged(it) },
            errorMessageRes = state.errorMessage,
            isEditMode = state.targetDayId != null,
            onConfirm = { viewModel.submitDialogInput() },
            onDismiss = { viewModel.closeInputDialog() }
        )
    }

    state.dayPendingDeletion?.let { day ->
        DeleteConfirmationDialog(
            dayName = day.name,
            onConfirm = { viewModel.deleteWorkoutDay(day.id) },
            onDismiss = { viewModel.dismissDeleteConfirmation() }
        )
    }
}

@Composable
private fun InputDialog(
    title: String,
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessageRes: Int?,
    isEditMode: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Second,
        title = {
            Text(
                text = title,
                style = DialogBoxTextStyle
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Background, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = text,
                        onValueChange = onTextChanged,
                        textStyle = TextStyle(
                            fontFamily = InterFont,
                            fontSize = 16.sp,
                            color = FirstText
                        ),
                        cursorBrush = SolidColor(Accent),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.enter_the_name),
                            color = SecondText.copy(alpha = 0.5f),
                            fontFamily = InterFont,
                            fontSize = 16.sp
                        )
                    }
                }

                errorMessageRes?.let { errorRes ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(errorRes),
                        color = Garbage.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        fontFamily = InterFont
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = if (isEditMode) stringResource(R.string.save_lower) else stringResource(R.string.add),
                    color = Accent,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = SecondText
                )
            }
        }
    )
}

@Composable
private fun DeleteConfirmationDialog(
    dayName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Second,
        title = {
            Text(
                text = stringResource(R.string.delete_day_title),
                style = DialogBoxTextStyle
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_day_confirm, dayName),
                color = SecondText,
                fontFamily = InterFont
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.delete),
                    color = Garbage.copy(alpha = 0.8F),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = SecondText
                )
            }
        }
    )
}
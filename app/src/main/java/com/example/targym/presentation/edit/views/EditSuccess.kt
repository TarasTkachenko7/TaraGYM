package com.example.targym.presentation.edit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.targym.R
import com.example.targym.presentation.edit.components.AddRepetitionButton
import com.example.targym.presentation.edit.components.EditScreenHeader
import com.example.targym.presentation.edit.components.ExerciseTitleSection
import com.example.targym.presentation.edit.components.NotesSection
import com.example.targym.presentation.edit.components.RepetitionsHeader
import com.example.targym.presentation.edit.components.SaveButton
import com.example.targym.presentation.edit.components.SetRowItem
import com.example.targym.presentation.edit.state.EditUiAction
import com.example.targym.presentation.edit.state.EditUiState
import com.example.targym.ui.theme.Background

@Composable
fun EditSuccess(
    uiState: EditUiState,
    onAction: (EditUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
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
            EditScreenHeader(
                title = if (uiState.exerciseId == -1L) stringResource(R.string.add) else stringResource(R.string.editing_exercise),
                isMenuExpanded = uiState.isMenuExpanded,
                onNavigationClick = { onAction(EditUiAction.NavigateBack) },
                onMoreClick = { onAction(EditUiAction.ToggleMenu(true)) },
                onDismissMenu = { onAction(EditUiAction.ToggleMenu(false)) },
                onRenameClick = { onAction(EditUiAction.OpenRenameDialog) },
                onDeleteClick = { onAction(EditUiAction.OpenDeleteConfirmation) }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp)
                    .imePadding(),
                contentPadding = PaddingValues(bottom = 180.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    ExerciseTitleSection(title = uiState.name.ifBlank { stringResource(R.string.new_exercise) })
                    Spacer(modifier = Modifier.height(32.dp))
                    RepetitionsHeader()
                    Spacer(modifier = Modifier.height(4.dp))
                }

                itemsIndexed(
                    items = uiState.repetitions,
                    key = { _, rep -> rep.id }
                ) { index, repetition ->
                    SetRowItem(
                        setNumber = index + 1,
                        weight = repetition.weight,
                        reps = repetition.quantity,
                        onWeightChange = { newWeight ->
                            onAction(EditUiAction.RepetitionChanged(repetition.id, newWeight, repetition.quantity))
                        },
                        onRepsChange = { newReps ->
                            onAction(EditUiAction.RepetitionChanged(repetition.id, repetition.weight, newReps))
                        },
                        onDeleteClick = { onAction(EditUiAction.RemoveRepetition(repetition.id)) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    AddRepetitionButton(onClick = { onAction(EditUiAction.AddRepetition) })
                    Spacer(modifier = Modifier.height(28.dp))
                    NotesSection(
                        noteText = uiState.note,
                        onNoteChange = { onAction(EditUiAction.NoteChanged(it)) }
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SaveButton(
                onClick = { onAction(EditUiAction.SaveExercise) },
                enabled = uiState.isSaveEnabled,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Background)
                    .padding(24.dp)
            )
        }
    }
}
package com.example.targym.presentation.edit.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.targym.R
import com.example.targym.presentation.edit.EditUiState
import com.example.targym.presentation.edit.components.AddRepetitionButton
import com.example.targym.presentation.edit.components.EditScreenHeader
import com.example.targym.presentation.edit.components.ExerciseTitleSection
import com.example.targym.presentation.edit.components.NotesSection
import com.example.targym.presentation.edit.components.RepetitionsHeader
import com.example.targym.presentation.edit.components.SaveButton
import com.example.targym.presentation.edit.components.SetRowItem
import com.example.targym.ui.theme.Background

@Composable
fun EditSuccess(
    uiState: EditUiState,
    onNavigationClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onRepetitionChange: (Long, String, String) -> Unit,
    onAddRepetition: () -> Unit,
    onRemoveRepetition: (Long) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = Background,
        topBar = {
            EditScreenHeader(
                title = if (uiState.exerciseId == -1L) stringResource(R.string.add) else stringResource(R.string.edit),
                onNavigationClick = onNavigationClick
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                            onRepetitionChange(repetition.id, newWeight, repetition.quantity)
                        },
                        onRepsChange = { newReps ->
                            onRepetitionChange(repetition.id, repetition.weight, newReps)
                        },
                        onDeleteClick = { onRemoveRepetition(repetition.id) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    AddRepetitionButton(onClick = onAddRepetition)
                    Spacer(modifier = Modifier.height(28.dp))
                    NotesSection(
                        noteText = uiState.note,
                        onNoteChange = onNoteChange
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    SaveButton(
                        onClick = onSaveClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}
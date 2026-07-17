package com.example.targym.presentation.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.edit.views.EditLoading
import com.example.targym.presentation.edit.views.EditSuccess

@Composable
fun EditScreen(
    viewModel: EditViewModel,
    exerciseId: Long,
    dayId: Long,
    muscleGroup: MuscleGroup,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = exerciseId, key2 = dayId) {
        viewModel.initExercise(exerciseId, dayId, muscleGroup)
    }

    LaunchedEffect(key1 = uiState.isSaved) {
        if (uiState.isSaved) {
            onNavigationClick()
        }
    }

    if (uiState.isLoading) {
        EditLoading(modifier = modifier)
    } else {
        EditSuccess(
            uiState = uiState,
            onNavigationClick = {
                if (viewModel.hasUnsavedChanges()) {
                    onNavigationClick()
                } else {
                    onNavigationClick()
                }
            },
            onNameChange = { viewModel.onNameChange(it) },
            onNoteChange = { viewModel.onNoteChange(it) },
            onRepetitionChange = { repId, weight, reps ->
                viewModel.onRepetitionChange(repId, weight, reps)
            },
            onAddRepetition = { viewModel.addRepetition() },
            onRemoveRepetition = { repId -> viewModel.removeRepetition(repId) },
            onSaveClick = { viewModel.saveExercise() },
            modifier = modifier
        )
    }
}
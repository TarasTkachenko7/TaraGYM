package com.example.targym.presentation.edit

import android.R.attr.singleLine
import android.R.attr.textStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.edit.views.EditLoading
import com.example.targym.presentation.edit.views.EditSuccess
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.R

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
            onNavigationClick = { onNavigationClick() },
            onMoreClick = { viewModel.toggleMenu(true) },
            onDismissMenu = { viewModel.toggleMenu(false) },
            onRenameClick = { viewModel.openRenameDialog() },
            onDeleteClick = { viewModel.deleteExercise() },
            onNameChange = { viewModel.onNameChange(it) },
            onNoteChange = { viewModel.onNoteChange(it) },
            onRepetitionChange = { repId, weight, reps -> viewModel.onRepetitionChange(repId, weight, reps) },
            onAddRepetition = { viewModel.addRepetition() },
            onRemoveRepetition = { repId -> viewModel.removeRepetition(repId) },
            onSaveClick = { viewModel.saveExercise() },
            modifier = modifier
        )
    }

    if (uiState.isRenameDialogOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.closeRenameDialog() },
            containerColor = Second,
            title = {
                Text(
                    text = stringResource(R.string.change_name),
                    style = TextStyle(
                        color = FirstText,
                        fontFamily = InterFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
            },
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Background, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = uiState.tempNameInput,
                        onValueChange = { viewModel.onTempNameChanged(it) },
                        textStyle = TextStyle(fontFamily = InterFont, fontSize = 16.sp, color = FirstText),
                        cursorBrush = SolidColor(FirstText),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.confirmRename() }) {
                    Text(text = stringResource(R.string.save_lower), color = Accent, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.closeRenameDialog() }) {
                    Text(text = stringResource(R.string.cancel), color = SecondText)
                }
            }
        )
    }
}
package com.example.targym.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.R
import com.example.targym.presentation.main.components.MuscleGroupBottomSheet
import com.example.targym.presentation.main.state.MainScreenState
import com.example.targym.presentation.main.state.MainUiAction
import com.example.targym.presentation.main.views.MainEmpty
import com.example.targym.presentation.main.views.MainLoading
import com.example.targym.presentation.main.views.MainSuccess
import com.example.targym.presentation.mapper.titleRes
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.DialogBoxTextStyle
import com.example.targym.ui.theme.Garbage
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigate: (MainUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    val handleAction: (MainUiAction) -> Unit = { action ->
        when (action) {
            is MainUiAction.OpenManageDays,
            is MainUiAction.AddExercise,
            is MainUiAction.OpenVideo,
            is MainUiAction.OpenEditExercise -> onNavigate(action)

            else -> viewModel.onAction(action)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        when (val state = screenState) {
            is MainScreenState.Loading -> {
                MainLoading()
            }
            is MainScreenState.Empty -> {
                MainEmpty(
                    onAddDayClick = { handleAction(MainUiAction.OpenManageDays) }
                )
            }
            is MainScreenState.Success -> {
                MainSuccess(
                    uiState = state.uiState,
                    onAction = handleAction
                )

                if (state.uiState.isMuscleBottomSheetOpen) {
                    MuscleGroupBottomSheet(
                        availableGroups = state.uiState.availableMuscleGroups,
                        onDismissRequest = { handleAction(MainUiAction.CloseMuscleBottomSheet) },
                        onMuscleGroupClick = { muscleGroup -> handleAction(MainUiAction.AddMuscleGroup(muscleGroup)) }
                    )
                }

                state.uiState.muscleGroupPendingDeletion?.let { muscleGroup ->
                    AlertDialog(
                        onDismissRequest = { handleAction(MainUiAction.DismissDeleteMuscleGroupDialog) },
                        containerColor = Second,
                        title = {
                            Text(
                                text = stringResource(R.string.delete_muscle_group_title),
                                style = DialogBoxTextStyle
                            )
                        },
                        text = {
                            Text(
                                text = stringResource(
                                    R.string.delete_muscle_group_confirm,
                                    stringResource(muscleGroup.titleRes)
                                ),
                                style = TextStyle(
                                    color = SecondText,
                                    fontFamily = InterFont,
                                    fontSize = 15.sp
                                )
                            )
                        },
                        confirmButton = {
                            TextButton(onClick = { handleAction(MainUiAction.ConfirmDeleteMuscleGroup) }) {
                                Text(
                                    text = stringResource(R.string.delete),
                                    color = Garbage.copy(alpha = 0.8f),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { handleAction(MainUiAction.DismissDeleteMuscleGroupDialog) }) {
                                Text(text = stringResource(R.string.cancel), color = SecondText)
                            }
                        }
                    )
                }
            }
        }
    }
}
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
import com.example.targym.presentation.main.components.MuscleGroupBottomSheet
import com.example.targym.presentation.main.views.MainEmpty
import com.example.targym.presentation.main.views.MainLoading
import com.example.targym.presentation.main.views.MainSuccess
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.Garbage
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.R
import com.example.targym.ui.theme.DialogBoxTextStyle

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
            is MainUiAction.SelectDay -> viewModel.selectDay(action.dayId)
            is MainUiAction.ToggleRepetition -> viewModel.toggleRepetitionCheck(action.exerciseId, action.repetitionId)
            is MainUiAction.DeleteMuscleGroup -> viewModel.removeMuscleGroup(action.muscleGroup)
            is MainUiAction.FinishWorkout -> viewModel.finishCurrentWorkout(action.workoutDayId)
            is MainUiAction.OpenMuscleBottomSheet -> viewModel.openMuscleBottomSheet()
            is MainUiAction.CloseMuscleBottomSheet -> viewModel.closeMuscleBottomSheet()
            is MainUiAction.AddMuscleGroup -> viewModel.addMuscleGroup(action.muscleGroup)
            is MainUiAction.ToggleMuscleMenu -> {
                if (action.isOpen) viewModel.openMuscleMenu(action.muscleGroup)
                else viewModel.closeMuscleMenu()
            }
            is MainUiAction.RequestDeleteMuscleGroup -> viewModel.requestDeleteMuscleGroup(action.muscleGroup)
            is MainUiAction.ConfirmDeleteMuscleGroup -> viewModel.confirmDeleteMuscleGroup()
            is MainUiAction.DismissDeleteMuscleGroupDialog -> viewModel.dismissDeleteMuscleGroupDialog()

            is MainUiAction.OpenManageDays,
            is MainUiAction.AddExercise,
            is MainUiAction.OpenVideo,
            is MainUiAction.OpenEditExercise -> onNavigate(action)
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
                    onDaySelected = { dayId -> handleAction(MainUiAction.SelectDay(dayId)) },
                    onRepetitionClick = { exId, repId -> handleAction(MainUiAction.ToggleRepetition(exId, repId)) },
                    onMenuClick = { handleAction(MainUiAction.OpenManageDays) },
                    onMuscleGroupButton = { handleAction(MainUiAction.OpenMuscleBottomSheet) },
                    onMenuToggle = { muscleGroup, open -> handleAction(MainUiAction.ToggleMuscleMenu(muscleGroup, open)) },
                    onAddExerciseClick = { muscleGroup -> handleAction(MainUiAction.AddExercise(dayId = state.uiState.selectedDayId, muscleGroup)) },
                    onDeleteGroupClick = { muscleGroup -> handleAction(MainUiAction.RequestDeleteMuscleGroup(muscleGroup)) },
                    onVideoClick = { exId -> handleAction(MainUiAction.OpenVideo(exId)) },
                    onEditClick = { exerciseId, muscleGroup ->
                        handleAction(
                            MainUiAction.OpenEditExercise(
                                exerciseId = exerciseId,
                                dayId = state.uiState.selectedDayId,
                                muscleGroup = muscleGroup
                            )
                        )
                    },
                    onFinishWorkoutClick = { dayId -> handleAction(MainUiAction.FinishWorkout(dayId)) }
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
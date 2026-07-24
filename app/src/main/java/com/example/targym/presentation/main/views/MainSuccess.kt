package com.example.targym.presentation.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.targym.presentation.main.components.DayEmptyState
import com.example.targym.presentation.main.components.ExercisesList
import com.example.targym.presentation.main.components.WorkoutDaysMenu
import com.example.targym.presentation.main.components.buttons.AddMuscleGroupFAB
import com.example.targym.presentation.main.state.MainUiAction
import com.example.targym.presentation.main.state.MainUiState
import com.example.targym.ui.theme.Background

@Composable
fun MainSuccess(
    uiState: MainUiState,
    onAction: (MainUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val isDayEmpty = uiState.groupedExercises.isEmpty()

    LaunchedEffect(key1 = uiState.selectedDayId) {
        lazyListState.scrollToItem(0)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WorkoutDaysMenu(
                items = uiState.workoutDays,
                onDayClick = { dayId -> onAction(MainUiAction.SelectDay(dayId)) },
                onMenuClick = { onAction(MainUiAction.OpenManageDays) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (isDayEmpty) {
                DayEmptyState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp)
                )
            } else {
                ExercisesList(
                    lazyListState = lazyListState,
                    selectedDayId = uiState.selectedDayId,
                    groupedExercises = uiState.groupedExercises,
                    activeMuscleMenuGroup = uiState.activeMuscleMenuGroup,
                    hasActiveWorkout = uiState.hasActiveWorkout,
                    onRepetitionClick = { exId, repId -> onAction(MainUiAction.ToggleRepetition(exId, repId)) },
                    onMenuToggle = { muscleGroup, open -> onAction(MainUiAction.ToggleMuscleMenu(muscleGroup, open)) },
                    onAddExerciseClick = { muscleGroup -> onAction(MainUiAction.AddExercise(muscleGroup)) },
                    onDeleteGroupClick = { muscleGroup -> onAction(MainUiAction.RequestDeleteMuscleGroup(muscleGroup)) },
                    onVideoClick = { exId -> onAction(MainUiAction.OpenVideo(exId)) },
                    onEditClick = { exId, _ -> onAction(MainUiAction.OpenEditExercise(exId)) },
                    onFinishWorkoutClick = { dayId -> onAction(MainUiAction.FinishWorkout(dayId)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }

        AddMuscleGroupFAB(
            onClick = { onAction(MainUiAction.OpenMuscleBottomSheet) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(bottom = 16.dp, end = 20.dp)
        )
    }
}
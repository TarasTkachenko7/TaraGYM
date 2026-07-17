package com.example.targym.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.targym.presentation.main.views.MainEmpty
import com.example.targym.presentation.main.views.MainLoading
import com.example.targym.presentation.main.views.MainSuccess
import com.example.targym.ui.theme.Background

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

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
                    onAddDayClick = {
                        // Обработка клика "Добавить тренировочный день"
                    }
                )
            }
            is MainScreenState.Success -> {
                MainSuccess(
                    uiState = state.uiState,
                    onDaySelected = { dayId ->
                        viewModel.selectDay(dayId)
                    },
                    onRepetitionClick = { exerciseId, repId ->
                        viewModel.toggleRepetitionCheck(exerciseId, repId)
                    },
                    onMenuClick = {
                        // Обработка клика меню дней (три полоски)
                    },
                    onMuscleGroupButton = {
                        // Переход на экран добавления группы мышц
                    },
                    onMuscleMenu = {
                        // Клик по трем точкам у группы мышц
                    },
                    onVideoClick = {

                    },
                    onEditClick = {

                    },
                    onFinishWorkoutClick = { dayId ->
                        viewModel.finishCurrentWorkout(dayId)
                    }
                )
            }
        }
    }
}
package com.example.targym.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.targym.presentation.days.ManageDaysScreen
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.main.MainScreen
import com.example.targym.presentation.main.MainUiAction
import com.example.targym.presentation.main.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Main
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Screen.Main> {
            val viewModel: MainViewModel = koinViewModel()

            MainScreen(
                viewModel = viewModel,
                onNavigate = { action ->
                    when (action) {
                        is MainUiAction.OpenManageDays -> {
                            navController.navigate(Screen.ManageDays)
                        }
                        is MainUiAction.AddExercise -> {
                            // Будущий экран добавления упражнения
                        }
                        is MainUiAction.OpenVideo -> {
                            // Будущий экран видео
                        }
                        is MainUiAction.OpenEditExercise -> {
                            // Будущий экран редактирования
                        }
                        // Все остальные экшены (SelectDay, ToggleRepetition...) сюда
                        // даже не долетят, их отфильтровал handleAction внутри MainScreen!
                        else -> {}
                    }
                }
            )
        }

        composable<Screen.ManageDays> {
            val viewModel: ManageDaysViewModel = koinViewModel()

            ManageDaysScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
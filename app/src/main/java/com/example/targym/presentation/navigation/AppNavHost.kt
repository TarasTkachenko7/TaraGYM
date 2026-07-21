package com.example.targym.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.targym.presentation.days.ManageDaysScreen
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.edit.EditScreen
import com.example.targym.presentation.edit.EditViewModel
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
                            navController.navigate(Screen.Edit(
                                exerciseId = -1L,
                                dayId = action.dayId,
                                muscleGroup = action.muscleGroup
                            ))
                        }
                        is MainUiAction.OpenVideo -> {
                            // Будущий экран видео
                        }
                        is MainUiAction.OpenEditExercise -> {
                            navController.navigate(Screen.Edit(
                                exerciseId = action.exerciseId,
                                dayId = action.dayId,
                                muscleGroup = action.muscleGroup
                            ))
                        }
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

        composable<Screen.Edit> { backStackEntry ->
            val route: Screen.Edit = backStackEntry.toRoute()
            val viewModel: EditViewModel = koinViewModel()

            EditScreen(
                viewModel = viewModel,
                exerciseId = route.exerciseId,
                dayId = route.dayId,
                muscleGroup = route.muscleGroup,
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

    }
}
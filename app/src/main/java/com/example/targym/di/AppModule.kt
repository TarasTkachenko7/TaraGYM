package com.example.targym.di

import android.system.Os.bind
import com.example.targym.data.impl.ExerciseRepositoryImpl
import com.example.targym.data.impl.WorkoutDayRepositoryImpl
import com.example.targym.domain.repository.ExerciseRepository
import com.example.targym.domain.repository.WorkoutDayRepository
import com.example.targym.domain.usecase.exercise.DeleteExerciseUseCase
import com.example.targym.domain.usecase.exercise.DeleteExercisesByMuscleGroupUseCase
import com.example.targym.domain.usecase.exercise.GetExerciseByIdUseCase
import com.example.targym.domain.usecase.exercise.GetExercisesByDayUseCase
import com.example.targym.domain.usecase.exercise.SaveExerciseUseCase
import com.example.targym.domain.usecase.workout.FinishWorkoutUseCase
import com.example.targym.domain.usecase.workout.ToggleRepetitionDoneUseCase
import com.example.targym.domain.usecase.workoutday.AddWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.DeleteWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.GetWorkoutDaysUseCase
import com.example.targym.domain.usecase.workoutday.UpdateWorkoutDayNameUseCase
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.edit.EditViewModel
import com.example.targym.presentation.gif.GifViewModel
import com.example.targym.presentation.main.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    singleOf(::WorkoutDayRepositoryImpl) bind WorkoutDayRepository::class
    singleOf(::ExerciseRepositoryImpl) bind ExerciseRepository::class

    factoryOf(::GetWorkoutDaysUseCase)
    factoryOf(::AddWorkoutDayUseCase)
    factoryOf(::UpdateWorkoutDayNameUseCase)
    factoryOf(::DeleteWorkoutDayUseCase)

    factoryOf(::GetExercisesByDayUseCase)
    factoryOf(::GetExerciseByIdUseCase)
    factoryOf(::SaveExerciseUseCase)
    factoryOf(::DeleteExerciseUseCase)
    factoryOf(::DeleteExercisesByMuscleGroupUseCase)

    factoryOf(::ToggleRepetitionDoneUseCase)
    factoryOf(::FinishWorkoutUseCase)

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        EditViewModel(get())
    }

    viewModel {
        ManageDaysViewModel(get())
    }

    viewModel { (exerciseId: Long) ->
        GifViewModel(exerciseId, get())
    }

}
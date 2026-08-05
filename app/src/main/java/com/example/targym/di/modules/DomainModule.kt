package com.example.targym.di.modules

import com.example.targym.domain.usecase.exercise.DeleteExerciseUseCase
import com.example.targym.domain.usecase.exercise.DeleteExercisesByMuscleGroupUseCase
import com.example.targym.domain.usecase.exercise.GetExerciseByIdUseCase
import com.example.targym.domain.usecase.exercise.GetExercisesByDayUseCase
import com.example.targym.domain.usecase.exercise.SaveExerciseUseCase
import com.example.targym.domain.usecase.exercise.UpdateExerciseMediaUseCase
import com.example.targym.domain.usecase.workout.FinishWorkoutUseCase
import com.example.targym.domain.usecase.workout.ToggleRepetitionDoneUseCase
import com.example.targym.domain.usecase.workoutday.AddWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.DeleteWorkoutDayUseCase
import com.example.targym.domain.usecase.workoutday.GetWorkoutDaysUseCase
import com.example.targym.domain.usecase.workoutday.UpdateWorkoutDayNameUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    // WorkoutDay UseCases
    factoryOf(::GetWorkoutDaysUseCase)
    factoryOf(::AddWorkoutDayUseCase)
    factoryOf(::UpdateWorkoutDayNameUseCase)
    factoryOf(::DeleteWorkoutDayUseCase)

    // Exercise UseCases
    factoryOf(::GetExercisesByDayUseCase)
    factoryOf(::GetExerciseByIdUseCase)
    factoryOf(::SaveExerciseUseCase)
    factoryOf(::DeleteExerciseUseCase)
    factoryOf(::DeleteExercisesByMuscleGroupUseCase)
    factoryOf(::UpdateExerciseMediaUseCase)

    // Workout Progress UseCases
    factoryOf(::ToggleRepetitionDoneUseCase)
    factoryOf(::FinishWorkoutUseCase)
}
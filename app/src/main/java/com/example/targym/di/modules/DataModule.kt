package com.example.targym.di.modules

import com.example.targym.data.impl.ExerciseRepositoryImpl
import com.example.targym.data.impl.WorkoutDayRepositoryImpl
import com.example.targym.domain.repository.ExerciseRepository
import com.example.targym.domain.repository.WorkoutDayRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::WorkoutDayRepositoryImpl) bind WorkoutDayRepository::class
    singleOf(::ExerciseRepositoryImpl) bind ExerciseRepository::class
}
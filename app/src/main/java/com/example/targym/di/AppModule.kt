package com.example.targym.di

import com.example.targym.data.impl.WorkoutRepositoryImpl
import com.example.targym.domain.repository.WorkoutRepository
import org.koin.dsl.module

val appModule = module {

    single<WorkoutRepository> {
        WorkoutRepositoryImpl()
    }

}
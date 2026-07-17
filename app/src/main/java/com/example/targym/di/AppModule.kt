package com.example.targym.di

import com.example.targym.data.impl.WorkoutRepositoryImpl
import com.example.targym.domain.repository.WorkoutRepository
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.edit.EditViewModel
import com.example.targym.presentation.main.MainViewModel
import org.koin.dsl.module

val appModule = module {

    single<WorkoutRepository> {
        WorkoutRepositoryImpl()
    }

    single {
        MainViewModel(get())
    }

    single {
        EditViewModel(get())
    }

    single {
        ManageDaysViewModel(get())
    }

}
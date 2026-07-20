package com.example.targym.di

import com.example.targym.data.impl.WorkoutRepositoryImpl
import com.example.targym.domain.repository.WorkoutRepository
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.edit.EditViewModel
import com.example.targym.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModule = module {

    single<WorkoutRepository> {
        WorkoutRepositoryImpl()
    }

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        EditViewModel(get())
    }

    viewModel {
        ManageDaysViewModel(get())
    }

}
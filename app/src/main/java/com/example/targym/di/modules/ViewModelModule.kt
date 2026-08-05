package com.example.targym.di.modules

import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.edit.EditViewModel
import com.example.targym.presentation.gif.GifViewModel
import com.example.targym.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::EditViewModel)
    viewModelOf(::ManageDaysViewModel)
    viewModelOf(::GifViewModel)
}
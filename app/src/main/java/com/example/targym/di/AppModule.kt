package com.example.targym.di

import com.example.targym.di.modules.dataModule
import com.example.targym.di.modules.dispatchersModule
import com.example.targym.di.modules.domainModule
import com.example.targym.di.modules.viewModelModule
import org.koin.dsl.module

val appModule = module {
    includes(
        dispatchersModule,
        dataModule,
        domainModule,
        viewModelModule
    )
}
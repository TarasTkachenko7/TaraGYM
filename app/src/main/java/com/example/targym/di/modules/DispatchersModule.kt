package com.example.targym.di.modules

import com.example.targym.domain.util.CoroutineDispatchers
import com.example.targym.domain.util.DefaultCoroutineDispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dispatchersModule = module {
    singleOf(::DefaultCoroutineDispatchers) bind CoroutineDispatchers::class
}
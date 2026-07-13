package com.example.targym

import android.app.Application
import com.example.targym.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TarGymApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TarGymApp)
            modules(appModule)
        }
    }
}
package com.honey.randomusergenerator.app

import android.app.Application
import com.honey.randomusergenerator.di.appModule
import com.honey.randomusergenerator.di.repositoryModule
import com.honey.randomusergenerator.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModules)
        }
    }
}
val appModules = listOf(
    appModule,
    repositoryModule,
    viewModelModule
)
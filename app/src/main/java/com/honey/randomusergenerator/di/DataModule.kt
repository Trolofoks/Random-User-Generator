package com.honey.randomusergenerator.di

import androidx.room.Room
import com.honey.data.external.RandomRepository
import com.honey.data.external.RandomRepositoryImpl
import com.honey.data.internal.savedusers.SavedRepository
import com.honey.data.internal.savedusers.SavedRepositoryImpl
import com.honey.data.internal.savedusers.sql.SavedDatabase
import com.honey.data.internal.settings.SettingsRepository
import com.honey.data.internal.settings.SettingsRepositoryImpl
import com.honey.data.model.Constance
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.compose.get
import org.koin.dsl.module

val dataModule = module {

    factory<RandomRepository> {
        RandomRepositoryImpl(
            api = get()
        )
    }

    factory<SavedRepository> {
        SavedRepositoryImpl(
            savedDatabase = get()
        )
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            SavedDatabase::class.java,
            Constance.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single<SettingsRepository>{
        SettingsRepositoryImpl(
            context = get()
        )
    }
}
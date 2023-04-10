package com.honey.randomusergenerator.di

import androidx.room.Room
import com.honey.data.random.RandomRepository
import com.honey.data.random.RandomRepositoryImpl
import com.honey.data.saved.SavedRepository
import com.honey.data.saved.SavedRepositoryImpl
import com.honey.data.saved.storage.sql.SavedDatabase
import com.honey.data.settings.SettingsRepository
import com.honey.data.settings.SettingsRepositoryImpl
import com.honey.data.saved.storage.sql.Constance
import org.koin.android.ext.koin.androidApplication
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
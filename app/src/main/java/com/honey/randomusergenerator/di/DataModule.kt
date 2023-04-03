package com.honey.randomusergenerator.di

import androidx.room.Room
import com.honey.data.external.RandomRepository
import com.honey.data.external.RandomRepositoryImpl
import com.honey.data.internal.SavedRepository
import com.honey.data.internal.SavedRepositoryImpl
import com.honey.data.internal.sql.SavedDatabase
import com.honey.data.model.Constance
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
}
package com.honey.randomusergenerator.di

import com.honey.data.external.RandomRepository
import com.honey.data.external.RandomRepositoryImpl
import com.honey.data.internal.SavedRepository
import com.honey.data.internal.SavedRepositoryImpl
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
}
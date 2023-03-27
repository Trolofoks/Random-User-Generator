package com.honey.randomusergenerator.di

import com.honey.data.external.RandomRepository
import com.honey.data.external.RandomRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    factory<RandomRepository> {
        RandomRepositoryImpl(
            api = get()
        )
    }
}
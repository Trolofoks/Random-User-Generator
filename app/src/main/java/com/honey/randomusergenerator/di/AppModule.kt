package com.honey.randomusergenerator.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("TODO")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
//        get<Retrofit>().create(TODO())
    }
    suspend fun <T> makeApiCall(
        dispatcher: CoroutineDispatcher,
        call: suspend () -> T
    ): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}

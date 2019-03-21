package org.fuzz.wellyrecycling.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.fuzz.wellyrecycling.network.WccRecyclingRepository
import org.fuzz.wellyrecycling.network.WccRecyclingRepositoryImpl
import org.fuzz.wellyrecycling.network.WccRecyclingJSONService
import org.fuzz.wellyrecycling.network.WccRecyclingRawService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single<WccRecyclingJSONService> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://wellington.govt.nz/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        retrofit.create()
    }

    single<WccRecyclingRawService> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://wellington.govt.nz/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        retrofit.create()
    }

    factory<WccRecyclingRepository> { WccRecyclingRepositoryImpl(get(), get()) }

}
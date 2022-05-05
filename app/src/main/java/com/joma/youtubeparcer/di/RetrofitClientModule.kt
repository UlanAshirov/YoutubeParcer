package com.joma.youtubeparcer.di

import com.joma.youtubeparcer.BuildConfig.BASE_URL
import com.joma.youtubeparcer.data.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { providerOkHttpClient() }
    single { providerRetrofit(get()) }
}

fun providerRetrofit(client: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
        .create(ApiService::class.java)
}

fun providerOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .apply {
            writeTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
}
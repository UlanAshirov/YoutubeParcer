package com.joma.youtubeparcer.di

import com.joma.youtubeparcer.BuildConfig.BASE_URL
import com.joma.youtubeparcer.data.implPlaylistRepo.ImplPlaylistRepository
import com.joma.youtubeparcer.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providerApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(client)
            }
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providerClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(2000, TimeUnit.SECONDS)
            .writeTimeout(2000, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(
                        HttpLoggingInterceptor
                            .Level
                            .BODY
                    )
            ).build()
    }

    @Provides
    @Singleton
    fun providerRepository(api: ApiService): ImplPlaylistRepository {
        return ImplPlaylistRepository(api)
    }
}
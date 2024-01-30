package com.prography.quest.di

import com.prography.quest.data.api.PhotosService
import com.prography.quest.data.api.RandomPhotoService
import com.prography.quest.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providePhotosService(retrofit: Retrofit): PhotosService {
        return retrofit.create(PhotosService::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomPhotoService(retrofit: Retrofit): RandomPhotoService {
        return retrofit.create(RandomPhotoService::class.java)
    }
}
package com.muhammetkudur.data.di.network

import com.muhammetkudur.data.api.MovieService
import com.muhammetkudur.data.interceptor.ApiKeyInterceptor
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

/**
 *    Created By Muhammet KÜDÜR
 *    17.07.2023
 */

private const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(moshiConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideMovieApiService(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): MovieService = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
            .create(MovieService::class.java)
}
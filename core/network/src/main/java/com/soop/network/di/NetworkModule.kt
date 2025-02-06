package com.soop.network.di

import androidx.tracing.trace
import com.soop.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val APPLICATION_JSON = "application/json"

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory =
        json.asConverterFactory(APPLICATION_JSON.toMediaType())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor { message ->
        when {
            message.startsWith("{") -> {
                Timber.tag("okhttp").d(JSONObject(message).toString(4))
            }

            message.startsWith("[") -> {
                Timber.tag("okhttp").d(JSONArray(message).toString(4))
            }

            else -> {
                Timber.tag("okhttp").d("CONNECTION INFO -> $message")
            }
        }
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(
        loggingInterceptor: Interceptor
    ): Call.Factory = trace("DaangnOkHttpClient") {
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        jsonConverter: Converter.Factory, okHttpCallFactory: Call.Factory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(SOOP_BASE_URL).addConverterFactory(jsonConverter)
            .callFactory(okHttpCallFactory).build()
    }
}

private const val SOOP_BASE_URL = "https://api.github.com"

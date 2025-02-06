package com.soop.network.di

import com.soop.network.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)
}
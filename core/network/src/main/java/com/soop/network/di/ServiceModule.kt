package com.soop.network.di

import com.soop.network.RepositoryService
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
    fun provideRepositoryService(retrofit: Retrofit): RepositoryService =
        retrofit.create(RepositoryService::class.java)
}
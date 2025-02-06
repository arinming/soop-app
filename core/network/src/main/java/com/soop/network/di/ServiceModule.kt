package com.soop.network.di

import com.soop.network.service.GithubService
import com.soop.network.service.RepositoryDetailService
import com.soop.network.service.UserDetailService
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

    @Provides
    @Singleton
    fun provideRepositoryDetailService(retrofit: Retrofit): RepositoryDetailService =
        retrofit.create(RepositoryDetailService::class.java)

    @Provides
    @Singleton
    fun provideUserDetailService(retrofit: Retrofit): UserDetailService =
        retrofit.create(UserDetailService::class.java)
}
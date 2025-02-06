package com.soop.data.di

import com.soop.data.repository.GithubRepository
import com.soop.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GithubRepositoryModule {
    @Binds
    abstract fun bindsGithubRepository(
        githubRepositoryImpl: GithubRepositoryImpl
    ): GithubRepository
}
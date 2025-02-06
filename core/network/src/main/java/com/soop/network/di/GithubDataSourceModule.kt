package com.soop.network.di

import com.soop.network.datasource.GithubDataSource
import com.soop.network.datasource.GithubDataSourceImpl
import com.soop.network.datasource.RepositoryDetailDataSource
import com.soop.network.datasource.RepositoryDetailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GithubDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsGithubDataSource(
        githubDataSourceImpl: GithubDataSourceImpl
    ): GithubDataSource

    @Binds
    @Singleton
    abstract fun bindsRepositoryDetailDataSource(
        repositoryDetailDataSourceImpl: RepositoryDetailDataSourceImpl
    ): RepositoryDetailDataSource
}
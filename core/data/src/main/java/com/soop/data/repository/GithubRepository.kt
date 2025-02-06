package com.soop.data.repository

import androidx.paging.PagingData
import com.soop.model.GithubRepositoryInfo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getGithub(
        query: String,
    ): Flow<PagingData<GithubRepositoryInfo>>
}
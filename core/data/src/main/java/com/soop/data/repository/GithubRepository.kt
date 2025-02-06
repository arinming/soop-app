package com.soop.data.repository

import androidx.paging.PagingData
import com.soop.model.GithubRepositoryInfo
import com.soop.model.RepositoryDetail
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getGithub(
        query: String,
    ): Flow<PagingData<GithubRepositoryInfo>>

    fun getRepositoryDetail(
        owner: String,
        repo: String
    ): Flow<RepositoryDetail>
}
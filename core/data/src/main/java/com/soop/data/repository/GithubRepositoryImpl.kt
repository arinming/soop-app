package com.soop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.soop.data.GithubPagingSource
import com.soop.model.GithubRepositoryInfo
import com.soop.model.RepositoryDetail
import com.soop.model.UserDetail
import com.soop.network.datasource.GithubDataSource
import com.soop.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val dataSource: GithubDataSource
) : GithubRepository {
    override fun getGithub(
        query: String,
    ): Flow<PagingData<GithubRepositoryInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
            ),
            pagingSourceFactory = { GithubPagingSource(query, dataSource) }
        ).flow.map { pagingData ->
            pagingData.map {
                it.second.asExternalModel()
            }
        }
    }

    override fun getRepositoryDetail(
        owner: String, repo: String
    ): Flow<RepositoryDetail> {
        return flow {
            val networkRepoDetail = dataSource.getRepositoryDetail(owner, repo)
            emit(networkRepoDetail.asExternalModel())
        }
    }

    override fun getUserDetail(
        username: String
    ): Flow<UserDetail> {
        return flow {
            val networkUserDetail = dataSource.getUserDetail(username)
            emit(networkUserDetail.asExternalModel())
        }
    }
}

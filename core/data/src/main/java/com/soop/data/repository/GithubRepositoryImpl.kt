package com.soop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.soop.data.GithubPagingSource
import com.soop.model.GithubRepositoryInfo
import com.soop.model.RepositoryDetail
import com.soop.model.UserDetail
import com.soop.model.UserLanguage
import com.soop.network.datasource.GithubDataSource
import com.soop.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val dataSource: GithubDataSource
) : GithubRepository {

    override fun getGithub(query: String): Flow<PagingData<GithubRepositoryInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { GithubPagingSource(query, dataSource) }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.second.asExternalModel()
                }
            }
            .catch {
                emit(PagingData.empty())
            }
    }

    override fun getRepositoryDetail(owner: String, repo: String): Flow<RepositoryDetail> {
        return flow {
            try {
                val networkRepoDetail = dataSource.getRepositoryDetail(owner, repo)
                emit(networkRepoDetail.asExternalModel())
            } catch (e: Exception) {
                emit(RepositoryDetail())
            }
        }.catch {
            emit(RepositoryDetail())
        }
    }

    override fun getUserDetail(username: String): Flow<UserDetail> {
        return flow {
            try {
                val networkUserDetail = dataSource.getUserDetail(username)
                emit(networkUserDetail.asExternalModel())
            } catch (e: Exception) {
                emit(UserDetail())
            }
        }.catch {
            emit(UserDetail())
        }
    }

    override fun getUserLanguage(username: String): Flow<List<UserLanguage>> {
        return flow {
            try {
                val networkUserLanguage = dataSource.getUserLanguage(username)
                emit(networkUserLanguage.map { it.asExternalModel() })
            } catch (e: Exception) {
                emit(emptyList())
            }
        }.catch {
            emit(emptyList())
        }
    }
}

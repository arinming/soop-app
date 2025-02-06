package com.soop.network.datasource

import com.soop.network.model.NetworkRepositoryDetail
import com.soop.network.service.RepositoryDetailService
import javax.inject.Inject

class RepositoryDetailDataSourceImpl @Inject constructor(
    private val repositoryDetailService: RepositoryDetailService
) : RepositoryDetailDataSource {
    override suspend fun getRepositoryDetail(
        owner: String, repo: String
    ): NetworkRepositoryDetail =
        repositoryDetailService.getRepositoryDetail(
            owner,
            repo
        )
}
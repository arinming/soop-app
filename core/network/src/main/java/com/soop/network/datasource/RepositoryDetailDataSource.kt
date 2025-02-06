package com.soop.network.datasource

import com.soop.network.model.NetworkRepositoryDetail

interface RepositoryDetailDataSource {
    suspend fun getRepositoryDetail(
        owner: String,
        repo: String,
    ): NetworkRepositoryDetail
}
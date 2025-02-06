package com.soop.network.datasource

import com.soop.network.model.NetworkGithubData
import com.soop.network.model.NetworkRepositoryDetail

interface GithubDataSource {
    suspend fun getGithub(
        query: String,
        sort: String = "stars",
        order: String = "desc",
        perPage: Int = 30,
        page: Int = 1
    ): NetworkGithubData

    suspend fun getRepositoryDetail(
        owner: String,
        repo: String
    ): NetworkRepositoryDetail
}
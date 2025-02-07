package com.soop.network.datasource

import com.soop.network.model.NetworkGithubData
import com.soop.network.model.NetworkRepositoryDetail
import com.soop.network.model.NetworkUserDetail
import com.soop.network.model.NetworkUserLanguage

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

    suspend fun getUserDetail(
        username: String
    ): NetworkUserDetail

    suspend fun getUserLanguage(
        username: String
    ): List<NetworkUserLanguage>
}
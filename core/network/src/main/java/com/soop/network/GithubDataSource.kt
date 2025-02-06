package com.soop.network

import com.soop.network.model.NetworkGithubData

interface GithubDataSource {
    suspend fun getGithub(
        query: String,
        sort: String = "stars",
        order: String = "desc",
        perPage: Int = 30,
        page: Int = 1
    ): NetworkGithubData
}
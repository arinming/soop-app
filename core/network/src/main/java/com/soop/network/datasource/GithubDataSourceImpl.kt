package com.soop.network.datasource

import com.soop.network.model.NetworkGithubData
import com.soop.network.service.GithubService
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : GithubDataSource {
    override suspend fun getGithub(
        query: String,
        sort: String,
        order: String,
        perPage: Int,
        page: Int
    ): NetworkGithubData = githubService.getGithub(
        query = query,
        sort = sort,
        order = order,
        perPage = perPage,
        page = page
    )
}
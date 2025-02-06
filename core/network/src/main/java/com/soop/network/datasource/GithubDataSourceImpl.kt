package com.soop.network.datasource

import com.soop.network.model.NetworkGithubData
import com.soop.network.model.NetworkRepositoryDetail
import com.soop.network.model.NetworkUserDetail
import com.soop.network.service.GithubService
import com.soop.network.service.RepositoryDetailService
import com.soop.network.service.UserDetailService
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val githubService: GithubService,
    private val repositoryDetailService: RepositoryDetailService,
    private val userDetailService: UserDetailService
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

    override suspend fun getRepositoryDetail(
        owner: String,
        repo: String
    ): NetworkRepositoryDetail = repositoryDetailService.getRepositoryDetail(
        owner = owner,
        repo = repo
    )

    override suspend fun getUserDetail(
        username: String
    ): NetworkUserDetail {
        return userDetailService.getUserDetail(
            username = username
        )
    }
}
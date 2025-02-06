package com.soop.network.service

import com.soop.network.model.NetworkRepositoryDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryDetailService {
    @GET("/repos/{owner}/{repo}")
    suspend fun getRepositoryDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): NetworkRepositoryDetail
}
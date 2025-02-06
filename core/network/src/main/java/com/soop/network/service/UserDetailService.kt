package com.soop.network.service

import com.soop.network.model.NetworkGithubData
import com.soop.network.model.NetworkUserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserDetailService {
    @GET("/users/{username}/repos")
    suspend fun getUserDetail(
        @Path("username") username: String,
    ): NetworkUserDetail
}
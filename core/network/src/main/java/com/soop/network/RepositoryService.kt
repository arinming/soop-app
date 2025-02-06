package com.soop.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    )
}
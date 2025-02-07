package com.soop.network.service

import com.soop.network.model.NetworkUserLanguage
import retrofit2.http.GET
import retrofit2.http.Path

interface UserLanguageService {
    @GET("/users/{username}/repos")
    suspend fun getUserLanguage(
        @Path("username") username: String
    ): List<NetworkUserLanguage>
}
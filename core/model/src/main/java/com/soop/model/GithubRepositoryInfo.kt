package com.soop.model

data class GithubRepositoryInfo(
    val name: String,
    val description: String?,
    val stars: Int,
    val language: String?,
    val username: String,
    val avatarUrl: String
)
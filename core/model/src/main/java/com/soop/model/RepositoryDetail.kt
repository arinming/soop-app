package com.soop.model

data class RepositoryDetail(
    val userName: String,
    val avatarUrl: String,
    val repoName: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val description: String?,
)
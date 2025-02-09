package com.soop.model

data class RepositoryDetail(
    val userName: String = "",
    val avatarUrl: String = "",
    val repoName: String = "",
    val stargazersCount: Int = 0,
    val watchersCount: Int = 0,
    val forksCount: Int = 0,
    val description: String? = "",
)
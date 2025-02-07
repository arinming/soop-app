package com.soop.network.model

import com.soop.model.GithubRepositoryInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGithubData(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("items")
    val repositories: List<Repository>
)

@Serializable
data class Repository(
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("description")
    val description: String?,
    @SerialName("stargazers_count")
    val stars: Int,
    @SerialName("language")
    val language: String?
)

@Serializable
data class Owner(
    @SerialName("login")
    val username: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)

fun Repository.asExternalModel(): GithubRepositoryInfo =
    GithubRepositoryInfo(
        name = this.owner.username,
        fullName = this.fullName,
        description = this.description,
        stars = this.stars,
        language = this.language,
        username = this.owner.username,
        avatarUrl = this.owner.avatarUrl
    )

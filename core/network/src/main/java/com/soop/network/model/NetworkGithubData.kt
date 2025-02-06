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

fun NetworkGithubData.asExternalModel(): List<GithubRepositoryInfo> =
    repositories.map { repo ->
        GithubRepositoryInfo(
            name = repo.name,
            description = repo.description,
            stars = repo.stars,
            language = repo.language,
            username = repo.owner.username,
            avatarUrl = repo.owner.avatarUrl
        )
    }
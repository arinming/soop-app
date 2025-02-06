package com.soop.network.model

import com.soop.model.RepositoryDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRepositoryDetail(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("owner")
    val ownerDetail: OwnerDetail,
    @SerialName("private")
    val private: Boolean,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("description")
    val description: String?,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("open_issues_count")
    val openIssuesCount: Int
)

@Serializable
data class OwnerDetail(
    @SerialName("login")
    val login: String,
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String
)

fun NetworkRepositoryDetail.asExternalModel(): RepositoryDetail =
    RepositoryDetail(
        userName = ownerDetail.login,
        avatarUrl = ownerDetail.avatarUrl,
        repoName = name,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        forksCount = forksCount,
        description = description
    )
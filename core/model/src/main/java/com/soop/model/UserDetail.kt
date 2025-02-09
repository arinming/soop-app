package com.soop.model

data class UserDetail(
    val id: Int = 0,
    val login: String = "",
    val avatarUrl: String = "",
    val followers: Int = 0,
    val following: Int = 0,
)

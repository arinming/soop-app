package com.soop.model

data class UserDetail(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val followers: Int,
    val following: Int,
)

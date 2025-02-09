package com.soop.network.model

import com.soop.model.UserLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUserLanguage(
    @SerialName("language") val language: String?,
)

fun NetworkUserLanguage.asExternalModel(): UserLanguage =
    UserLanguage(
        language = this.language ?: ""
    )

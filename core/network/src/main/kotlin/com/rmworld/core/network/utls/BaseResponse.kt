package com.rmworld.core.network.utls

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    @SerialName("statusCode")
    val statusCode: Int,
    @SerialName("message")
    val message: String,
)

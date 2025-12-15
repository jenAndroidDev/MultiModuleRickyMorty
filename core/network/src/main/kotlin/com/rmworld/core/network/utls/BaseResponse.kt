package com.rmworld.core.network.utls

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
)
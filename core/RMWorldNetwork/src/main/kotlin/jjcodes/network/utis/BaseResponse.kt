package jjcodes.network.utis

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
)
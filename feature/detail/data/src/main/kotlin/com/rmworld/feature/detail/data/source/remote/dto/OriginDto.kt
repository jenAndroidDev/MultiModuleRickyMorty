package com.rmworld.feature.detail.data.source.remote.dto

import com.rmworld.feature.detail.domain.model.Origin
import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    val name: String,
    val url: String
){
    fun toOrigin(): Origin {
        return Origin(
            name = name,
            url = url
        )
    }
}
package com.rmworld.feature.detail.data.source.remote.dto


import com.rmworld.feature.detail.domain.model.Location
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val name: String,
    val url: String
){
    fun toLocation(): Location {
        return Location(
            name = name,
            url = url
        )
    }
}
package com.rmworld.feature.home.data.source.remote.model


import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseModel(
    val info: Info,
    val results: List<Result>
)
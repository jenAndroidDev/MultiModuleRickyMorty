package com.rmworld.feature.detail.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseModel(
    val info: Info?=null,
    val results: List<Result>?=null
)
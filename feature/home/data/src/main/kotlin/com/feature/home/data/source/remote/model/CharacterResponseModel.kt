package com.feature.home.data.source.remote.model

import com.feature.home.data.source.remote.model.Info
import kotlinx.serialization.Serializable
import kotlin.Result

@Serializable
data class CharacterResponseModel(
    val info: Info,
    val results: List<com.feature.home.data.source.remote.model.Result>
)
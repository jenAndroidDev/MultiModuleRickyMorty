package com.rmworld.feature.detail.data.source.remote.model


import com.rmworld.feature.detail.data.source.remote.dto.LocationDto
import com.rmworld.feature.detail.data.source.remote.dto.OriginDto
import com.rmworld.feature.detail.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    fun toCharacter(): Character {
        return Character(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            location = location.toLocation(),
            origin = origin.toOrigin(),
            species = species,
            status = status,
            type = type,
            url = url,
            name = name
        )
    }
}
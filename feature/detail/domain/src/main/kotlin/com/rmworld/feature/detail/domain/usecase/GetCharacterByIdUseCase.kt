package com.rmworld.feature.detail.domain.usecase

import com.rmworld.feature.detail.domain.model.Character
import com.rmworld.feature.detail.domain.model.Location
import com.rmworld.feature.detail.domain.model.Origin
import com.rmworld.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    operator fun invoke(id:Int) = repository.getCharacterStream(id = id)

    private fun getFakeData(): Character {
        return Character(
            created = "",
            episode = listOf(),
            gender = "Male",
            id = 0,
            image = "",
            location = Location(name = "", url = ""),
            name = "Rick Sanchez",
            origin = Origin(name = "", url = ""),
        )
    }
}
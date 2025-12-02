package com.feature.home.domain.usecase

import com.feature.home.domain.model.Character
import com.feature.home.domain.model.Location
import com.feature.home.domain.model.Origin
import com.feature.home.domain.repository.HomeRepository
import com.rmworld.core.common.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor (
    private val repository: HomeRepository
){
    operator fun invoke() = flow{
        emit(Result.Loading)
        delay(1000)
        emit(Result.Success(mockCharacters)) //
    }

}
val mockCharacters = listOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        origin = Origin("Earth", ""),
        location = Location("Earth", ""),
        readStatus = true
    ),
    Character(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        origin = Origin("Earth", ""),
        location = Location("Earth", ""),
        readStatus = false
    )
)
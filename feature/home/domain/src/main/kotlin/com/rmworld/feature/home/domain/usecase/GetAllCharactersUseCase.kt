package com.rmworld.feature.home.domain.usecase

import com.rmworld.feature.home.domain.repository.HomeRepository
import javax.inject.Inject


class GetAllCharactersUseCase @Inject constructor (
    private val repository: HomeRepository
){
    suspend operator fun invoke() = repository.getAllCharacterStream()

}

package com.rmworld.feature.home.domain.usecase

import com.rmworld.feature.home.domain.repository.HomeRepository
import javax.inject.Inject


class GetAllCharactersUseCase @Inject constructor (
    private val repository: HomeRepository
){
     operator fun invoke(pageNo: Int) = repository.getAllCharacterStream(pageNo)

}

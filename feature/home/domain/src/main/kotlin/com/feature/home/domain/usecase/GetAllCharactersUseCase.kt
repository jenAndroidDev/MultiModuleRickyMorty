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
    suspend operator  fun invoke() = repository.getAllCharacterStream()

}

package com.feature.home.domain.source

import com.feature.home.domain.model.Character
import jjcodes.network.utis.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {

    fun getAllCharacters(): Flow<NetworkResult<Character>>
}
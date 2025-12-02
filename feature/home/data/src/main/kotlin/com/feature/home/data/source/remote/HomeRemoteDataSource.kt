package com.feature.home.data.source.remote

import com.feature.home.data.source.remote.model.CharacterResponseModel
import jjcodes.network.utis.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {

    fun getAllCharacters(): Flow<NetworkResult<CharacterResponseModel>>
}
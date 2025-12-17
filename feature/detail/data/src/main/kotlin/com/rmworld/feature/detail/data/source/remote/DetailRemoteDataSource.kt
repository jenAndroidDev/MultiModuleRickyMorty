package com.rmworld.feature.detail.data.source.remote

import com.rmworld.feature.detail.data.source.remote.model.CharacterResponseModel
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.feature.detail.data.source.remote.model.Result
import kotlinx.coroutines.flow.Flow

interface DetailRemoteDataSource {

    fun getCharacterById(id: Int): Flow<NetworkResult<Result>>
}
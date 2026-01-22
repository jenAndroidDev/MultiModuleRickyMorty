package com.rmworld.feature.home.data.source.remote

import com.rmworld.core.network.Dispatcher
import com.rmworld.core.network.RMWorldDispatchers
import com.rmworld.feature.home.data.source.remote.api.ApiService
import com.rmworld.feature.home.data.source.remote.model.CharacterResponseModel
import com.rmworld.core.network.utls.BaseRemoteDataSource
import com.rmworld.core.network.utls.NetworkHelper
import com.rmworld.core.network.utls.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    val apiService: ApiService,
     networkHelper: NetworkHelper,
    @Dispatcher(RMWorldDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): HomeRemoteDataSource, BaseRemoteDataSource(networkHelper) {
    override fun getAllCharacters(): Flow<NetworkResult<CharacterResponseModel>> = flow{
        emit(NetworkResult.Loading())
        emit(newSafeApiCall { apiService.getAllCharacters() })
    }.flowOn(ioDispatcher)
}
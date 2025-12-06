package com.feature.home.data.source.remote

import com.feature.home.data.source.remote.api.ApiService
import com.feature.home.data.source.remote.model.CharacterResponseModel
import jjcodes.network.utis.BaseRemoteDataSource
import jjcodes.network.utis.NetworkHelper
import jjcodes.network.utis.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    val apiService: ApiService,
     networkHelper: NetworkHelper
): HomeRemoteDataSource, BaseRemoteDataSource(networkHelper) {

    override fun getAllCharacters(): Flow<NetworkResult<CharacterResponseModel>> = flow {
        emit(NetworkResult.Loading())
        emit(safeApiCall { apiService.getAllCharacters() })
    }.flowOn(Dispatchers.IO)
}
package com.rmworld.feature.detail.data.source.remote

import com.rmworld.core.network.Dispatcher
import com.rmworld.core.network.RMWorldDispatchers
import com.rmworld.core.network.utls.BaseRemoteDataSource
import com.rmworld.core.network.utls.NetworkHelper
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.feature.detail.data.source.remote.api.DetailApiService
import com.rmworld.feature.detail.data.source.remote.model.CharacterResponseModel
import com.rmworld.feature.detail.data.source.remote.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(
    private val apiService: DetailApiService,
    networkHelper: NetworkHelper,
    @Dispatcher(RMWorldDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : DetailRemoteDataSource, BaseRemoteDataSource(networkHelper) {
    override fun getCharacterById(id: Int): Flow<NetworkResult<Result>> = flow{
        emit(NetworkResult.Loading())
        emit(newSafeApiCall { apiService.getCharacterById(id) })
    }.flowOn(ioDispatcher)


}
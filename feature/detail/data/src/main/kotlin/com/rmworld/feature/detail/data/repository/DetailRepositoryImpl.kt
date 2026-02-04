package com.rmworld.feature.detail.data.repository

import android.util.Log
import com.rmworld.core.common.Result
import com.rmworld.core.common.apiresult.NoInternetException
import com.rmworld.feature.detail.domain.model.Character
import com.rmworld.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.feature.detail.data.source.remote.DetailRemoteDataSource



class DetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepository {
    override fun getCharacterStream(id: Int): Flow<Result<Character>> {
        return remoteDataSource.getCharacterById(id).map { networkResult ->
            when (networkResult) {
                is NetworkResult.Loading -> {
                    Result.Loading
                }
                is NetworkResult.Success -> {
                    val characterDto = networkResult.data
                    if (characterDto != null) {
                        Result.Success(characterDto.toCharacter())
                    } else {
                        Result.Error(Exception("Character data is null"))
                    }
                }
                is NetworkResult.Error -> {
                    Result.Error(Exception(networkResult.message ?: "An unknown error occurred"))
                }
                is NetworkResult.NoInternet -> {
                    Result.Error(NoInternetException())
                }
                is NetworkResult.UnAuthorized -> {
                    Result.Error(Exception(networkResult.message ?: "Unauthorized"))
                }
            }
        }
    }
}
package com.rmworld.feature.home.data.repository

import com.rmworld.core.common.Result
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.feature.home.data.source.remote.HomeRemoteDataSource
import com.rmworld.feature.home.domain.model.Character
import com.rmworld.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override fun getAllCharacterStream(): Flow<Result<List<Character>>> {
        return remoteDataSource.getAllCharacters().map { networkResult ->
            when (networkResult) {
                is NetworkResult.Loading -> Result.Loading
                is NetworkResult.Success -> {
                    val domainCharacters =
                        networkResult.data?.results?.map { it.toCharacter() } ?: emptyList()
                    Result.Success(domainCharacters)
                }
                is NetworkResult.Error -> {
                    Result.Error(Exception(networkResult.message ?: "An unknown error occurred"))
                }
                is NetworkResult.NoInternet -> {
                    Result.Error(Exception(networkResult.message ?: "No internet connection"))
                }
                is NetworkResult.UnAuthorized -> {
                    Result.Error(Exception(networkResult.message ?: "Unauthorized"))
                }
            }
        }
    }
}

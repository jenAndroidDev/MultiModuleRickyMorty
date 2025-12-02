package com.feature.home.data.repository

import com.feature.home.data.source.remote.HomeRemoteDataSource
import com.feature.home.domain.model.Character
import com.feature.home.domain.repository.HomeRepository
import com.rmworld.core.common.Result
import com.rmworld.core.common.Result.*
import jjcodes.network.utis.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
): HomeRepository{
    override fun getAllCharacterStream(): Flow<Result<List<Character>>> {
       return  remoteDataSource.getAllCharacters().map { networkResult ->
            when (networkResult) {
                is NetworkResult.Loading -> { Loading }
                is NetworkResult.Success -> {
                    val domainCharacters = networkResult.data?.results?.map { it.toCharacter() } ?: emptyList()
                    Success(domainCharacters)
                }
                is NetworkResult.Error -> {
                    //will create a more generic type next time
                    Error(Exception(networkResult.message ?: "An unknown error occurred"))
                }
                is NetworkResult.NoInternet -> {
                    //will create a more generic type next time
                    Error(Exception(networkResult.message ?: "No internet connection"))
                }
                else -> {
                    Error(Exception(networkResult.message ?: "No internet connection"))
                }
            }
        }
    }
}
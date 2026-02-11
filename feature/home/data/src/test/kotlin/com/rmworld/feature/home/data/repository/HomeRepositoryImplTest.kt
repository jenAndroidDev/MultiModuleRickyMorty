package com.rmworld.feature.home.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.rmworld.core.common.Result as DomainResult
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.core.testing.utils.MainDispatcherRule
import com.rmworld.feature.home.data.source.remote.HomeRemoteDataSource
import com.rmworld.feature.home.data.source.remote.dto.LocationDto
import com.rmworld.feature.home.data.source.remote.dto.OriginDto
import com.rmworld.feature.home.data.source.remote.model.CharacterResponseModel
import com.rmworld.feature.home.data.source.remote.model.Info
import com.rmworld.feature.home.data.source.remote.model.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeRepositoryImplTest {

    @get:Rule
    val dispatcherRule: MainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var remoteDataSource: HomeRemoteDataSource

    private lateinit var repository: HomeRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = HomeRepositoryImpl(remoteDataSource)
    }

    @Test
    fun repositoryReturnsListOfCharacters() = runTest {
        every { remoteDataSource.getAllCharacters() } returns flowOf(NetworkResult.Success(mockResponse, message = "Success"))

        val testResults = repository.getAllCharacterStream()

        testResults.test {
            val result = awaitItem()
            assert(result is com.rmworld.core.common.Result.Success)
            cancelAndIgnoreRemainingEvents()
        }

        verify {
            remoteDataSource.getAllCharacters()
        }
    }
    @Test
    fun repositoryReturnsError() = runTest {
        every {
            remoteDataSource.getAllCharacters()
        } returns flowOf(NetworkResult.Error(
            message = mockErrorMsg,
            code = mockHttpErrorCode
        ))
        val testResults = repository.getAllCharacterStream()
        testResults.test {
            val result =awaitItem()
            assertThat(result).isInstanceOf(DomainResult.Error::class.java)
            val error = result as DomainResult.Error
            assertThat(error.exception.message).isEqualTo(mockErrorMsg)
            cancelAndIgnoreRemainingEvents()
        }
        verify { remoteDataSource.getAllCharacters() }

    }
}
private val mockResponse = CharacterResponseModel(
    info = Info(count = 1, pages = 1, next = "", prev = null),
    results = listOf(
        Result(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginDto(name = "Earth", url = ""),
            location = LocationDto(name = "Earth", url = ""),
            image = "",
            episode = listOf(),
            url = "",
            created = ""
        )
    )
)
private val mockErrorMsg = "Characters Not Found"
private val mockHttpErrorCode = 400

package com.rmworld.feature.detail.data

import com.google.common.truth.Truth.assertThat
import com.rmworld.core.common.Result as DomainResult
import app.cash.turbine.test
import com.rmworld.core.network.utls.NetworkResult
import com.rmworld.core.testing.utils.MainDispatcherRule
import com.rmworld.feature.detail.data.repository.DetailRepositoryImpl
import com.rmworld.feature.detail.data.source.remote.DetailRemoteDataSource
import com.rmworld.feature.detail.data.source.remote.dto.LocationDto
import com.rmworld.feature.detail.data.source.remote.dto.OriginDto
import com.rmworld.feature.detail.data.source.remote.model.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import net.bytebuddy.matcher.ElementMatchers.returns
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailRepositoryImplTest {
    @get:Rule
    val dispatcherRule: MainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var remoteDataSource: DetailRemoteDataSource

    private lateinit var repository: DetailRepositoryImpl

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        repository = DetailRepositoryImpl(remoteDataSource)

    }

    @Test
    fun repositoryReturnsLoading() = runTest {
        every { remoteDataSource.getCharacterById(id = mockCharacterId) } returns flowOf(
            NetworkResult.Loading())

        val testResults = repository.getCharacterStream(id = mockCharacterId)
        testResults.test {
            val item = awaitItem()
            assert(item is com.rmworld.core.common.Result.Loading)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun repositoryReturnsCharacterById() = runTest{
        every { remoteDataSource.getCharacterById(id = mockCharacterId) } returns flowOf(
            NetworkResult.Success(mockCharacterDto, message = "Success"))

        val testResults = repository.getCharacterStream(id = mockCharacterId)
        testResults.test {
            val result = awaitItem()
            assert(result is com.rmworld.core.common.Result.Success)
            cancelAndIgnoreRemainingEvents()
        }
        verify { remoteDataSource.getCharacterById(id = mockCharacterId) }
    }

    @Test
    fun repositoryReturnsError() = runTest{
        every { remoteDataSource.getCharacterById(id = mockCharacterId) } returns flowOf(
            NetworkResult.Error(message = mockErrorMsg, code = mockHttpErrorCode))

        val testResults = repository.getCharacterStream(id = mockCharacterId)
        testResults.test {
            val result = awaitItem()
            assertThat(result).isInstanceOf(DomainResult.Error::class.java)
            val error = result as DomainResult.Error
            assertThat(error.exception.message).isEqualTo(mockErrorMsg)
            cancelAndIgnoreRemainingEvents()
        }

        verify { remoteDataSource.getCharacterById(id = mockCharacterId) }

    }

}
private val mockCharacterId = 1

private val mockCharacterDto = Result(
    id = mockCharacterId,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    type = "",
    gender = "Male",
    origin = OriginDto(name = "Earth (C-137)", url = ""),
    location = LocationDto(name = "Citadel of Ricks", url = ""),
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episode = listOf("https://rickandmortyapi.com/api/episode/1"),
    url = "https://rickandmortyapi.com/api/character/1",
    created = "2017-11-04T18:48:46.250Z"
)

private val mockErrorMsg = "Character Not Found"
private val mockHttpErrorCode = 400


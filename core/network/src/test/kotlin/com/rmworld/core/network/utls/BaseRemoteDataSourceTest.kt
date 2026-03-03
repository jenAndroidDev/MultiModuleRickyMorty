package com.rmworld.core.network.utls

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue


class BaseRemoteDataSourceTest {

    private lateinit var dataSource: TestRemoteDataSource
    private val networkHelper: NetworkHelper = mockk()
    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        dataSource = TestRemoteDataSource(networkHelper)
        dataSource.json = json
    }

    @Test
    fun newSafeApiCall_hasInternet_returnsSuccess() = runTest {
        val expectedData = "Test Data"
        every { networkHelper.checkForInternet() } returns true
        val mockResponse = Response.success(expectedData)
        val result = dataSource.testSafeApiCall { mockResponse }
        assertTrue(result is NetworkResult.Success)
        assertEquals(expectedData, (result as NetworkResult.Success).data)
    }

    @Test
    fun newSafeApiCall_executesOnFailureLambda_onFatalException() = runTest {
        val exceptionMessage = "Network call failed unexpectedly"
        every { networkHelper.checkForInternet() } returns true

        val networkResult = dataSource.testSafeApiCall<String> {
            throw RuntimeException(exceptionMessage) 
        }
        assertTrue(networkResult is NetworkResult.Error)
        assertTrue(networkResult.message?.contains(exceptionMessage) == true)
        assertThat(networkResult).isInstanceOf(NetworkResult.Error::class.java)
    }


    class TestRemoteDataSource(netWorkHelper: NetworkHelper) : BaseRemoteDataSource(netWorkHelper) {
        suspend fun <T> testSafeApiCall(call: suspend () -> Response<T>) = newSafeApiCall(call)
    }
}

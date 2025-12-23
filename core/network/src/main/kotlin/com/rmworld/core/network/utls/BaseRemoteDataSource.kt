package com.rmworld.core.network.utls


import androidx.annotation.WorkerThread
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.Contract
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection


open class BaseRemoteDataSource(
    private val netWorkHelper: NetworkHelper,
) {

    private val logTag: String = BaseRemoteDataSource::class.java.simpleName

    @WorkerThread
    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
        return when {
            netWorkHelper.checkForInternet() -> {
                try {
                    call.invoke().let { response: Response<T> ->
                        Timber.d("Response: body ${response.body()} errorBody ${response.errorBody()} raw $response")
                        if (response.isSuccessful) {
                            response.body()?.let {
                                println("response_success>>$it")
                                NetworkResult.Success(
                                    it,
                                    response.message(),
                                    code = response.code()
                                )
                            } ?: error("Success. But no data")
                        } else {
                            apiErrorHandler(response)
                        }
                    }
                } catch (e: Exception) {
                    NetworkResult.Error(e.toString(), null)
                }
            }

            else -> NetworkResult.NoInternet("Unable to connect to the internet", null)
        }
    }

    @WorkerThread
    protected suspend fun <T> synchronizedCall(call: suspend () -> T): NetworkResult<T> {
        return try {
            val response = call.invoke() ?: throw IllegalStateException("Failed to make call.")
            Timber.d("Response: body $response")
            NetworkResult.Success(response, null)
        } catch (e: HttpException) {
            NetworkResult.Error(e.message(), code = e.code())
        } catch (e: IOException) {
            NetworkResult.Error(e.message ?: "Unknown Error")
        }
    }

    private fun <T> apiErrorHandler(response: Response<T>): NetworkResult<T> {
        val errorBodyJson = response.errorBody()?.string()
        Timber.d("Response: errorBody $errorBodyJson")
        val errorBody = parseErrorBodyJson(errorBodyJson)
        val message = response.message()
        var errorMessage: String
        try {
            when (val code = response.code()) {
                HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                    errorMessage = "$code ${HttpURLConnection.HTTP_INTERNAL_ERROR}"
                }

                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    errorMessage = "UnAuthorized"
                    return NetworkResult.UnAuthorized(errorMessage)
                }

                else -> {
                    errorMessage = "$code $message"
                }
            }
        } catch (e: Exception) {
            errorMessage = "${response.code()} $message"
        }
        Timber.d("Api Error: ${errorBody?.message}")
        return NetworkResult.Error(
            "Api Error Response: $errorMessage",
            uiMessage = errorBody?.message,
            code = response.code()
        )
    }

    @Contract("null -> null")
    private fun parseErrorBody(errorBodyJson: String?): BaseResponse? {
        Timber.d(logTag, "parseErrorBody: $errorBodyJson")
        errorBodyJson ?: return null
        return runCatching {
            Json.decodeFromString<BaseResponse>(errorBodyJson)
        }.getOrNull()
        /*return try {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return gson.fromJson(errorBodyJson, BaseResponse::class.java)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }*/
    }

    protected suspend fun <T>newSafeApiCall(
        call: suspend ()-> Response<T>
    ): NetworkResult<T>{
        if (!netWorkHelper.checkForInternet()){
           return NetworkResult.NoInternet("Unable to connect to the internet")
        }
        return runCatching { call() }.fold(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        NetworkResult.Success(
                            it,
                            response.message(),
                            code = response.code()
                        )

                    }?: NetworkResult.Error("Success. But no data")
                }else{
                    httpErrorHandler(response)
                }
            },
            onFailure = {exception ->
                if (exception is CancellationException) throw exception
                NetworkResult.Error(exception.toString())
            }
        )
    }
    private fun <T> httpErrorHandler(response: Response<T>): NetworkResult<T>{
        val errorBodyJson = response.errorBody()?.string()
        Timber.d("Response: errorBody $errorBodyJson")
        val errorBody = parseErrorBodyJson(errorBodyJson)
        var errorMessage = ""
       return runCatching {
            when(errorBody?.statusCode){
                HttpURLConnection.HTTP_INTERNAL_ERROR->{
                    errorMessage = "${errorBody.message} ${HttpURLConnection.HTTP_INTERNAL_ERROR}"
                }

                HttpURLConnection.HTTP_UNAUTHORIZED->{
                    errorMessage = "UnAuthorized User"
                    return NetworkResult.UnAuthorized(errorMessage)
                }
            }
        }.fold(
            onSuccess = {
                return NetworkResult.Error(
                "Api Error Response: $errorMessage",
                uiMessage = errorBody?.message,
                code = response.code()
            )},
            onFailure = {error->
                NetworkResult.Error(
                    message = "Failed to parse Error Body: ${error.message}",
                    code = HttpURLConnection.HTTP_VERSION
                )}
            )
    }
    private fun parseErrorBodyJson(errorBodyJson: String?)=
        runCatching {
            errorBodyJson?.let { error ->
                json.decodeFromString<BaseResponse>(error)
            }
        }.getOrNull()
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient =true
    }


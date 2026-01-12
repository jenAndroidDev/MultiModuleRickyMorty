package com.rmworld.core.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

private const val Tag = "ApiServiceFactory"

class ApiServiceFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val retrofitBuilder: Retrofit.Builder
) {

    private val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor{message ->
            Log.d(Tag, "null() called with: message = $message")

        }.apply {

        }
    }

    fun <T> create(
        serviceType: Class<T>,
        baseUrl: String,
        shouldAddSignInterceptor: Boolean = false,
        vararg extraInterceptors: Interceptor = emptyArray()
    ):T{
        val httpClient = okHttpClient.newBuilder()
            .apply {
                extraInterceptors.forEach(::addInterceptor)
            }.build()

        return retrofitBuilder.baseUrl(baseUrl)
            .callFactory(TimeoutCallFactory(httpClient))
            .build()
            .create(serviceType)
    }
}
package jjcodes.network

import java.util.concurrent.TimeUnit
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

private const val TIMEOUT = 30L
class TimeoutCallFactory(
    private val okHttpClient: OkHttpClient,

): Call.Factory {
    override fun newCall(request: Request): Call {
        val okHttpClient = okHttpClient.newBuilder()
            .connectTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = TIMEOUT, unit = TimeUnit.SECONDS)
            .build()
        return okHttpClient.newCall(request)
    }

}
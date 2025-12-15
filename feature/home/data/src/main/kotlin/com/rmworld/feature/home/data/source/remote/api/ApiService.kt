package com.rmworld.feature.home.data.source.remote.api

import com.rmworld.feature.home.data.source.remote.model.CharacterResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page:Int =2): Response<CharacterResponseModel>
}

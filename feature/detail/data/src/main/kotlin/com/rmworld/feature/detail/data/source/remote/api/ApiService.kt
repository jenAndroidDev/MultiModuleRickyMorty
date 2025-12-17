package com.rmworld.feature.detail.data.source.remote.api

import com.rmworld.feature.detail.data.source.remote.model.CharacterResponseModel
import com.rmworld.feature.detail.data.source.remote.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
* Will move into single Api Instance
* till that consider this as different api service*/
interface DetailApiService {
    @GET("character/{id}")
    suspend fun getCharacterById( @Path("id") characterId: Int): Response<Result>
}

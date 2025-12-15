package com.rmworld.feature.home.domain.repository

import com.rmworld.core.common.Result
import com.rmworld.feature.home.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllCharacterStream(): Flow<Result<List<Character>>>
}
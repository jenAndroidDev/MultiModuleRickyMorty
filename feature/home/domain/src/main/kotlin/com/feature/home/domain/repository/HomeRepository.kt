package com.feature.home.domain.repository

import com.feature.home.domain.model.Character
import com.rmworld.core.common.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllCharacterStream(): Flow<Result<List<Character>>>
}
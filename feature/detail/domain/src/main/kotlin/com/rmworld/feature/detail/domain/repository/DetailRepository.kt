package com.rmworld.feature.detail.domain.repository

import com.rmworld.core.common.Result
import com.rmworld.feature.detail.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getCharacterStream(id: Int): Flow<Result<Character>>
}


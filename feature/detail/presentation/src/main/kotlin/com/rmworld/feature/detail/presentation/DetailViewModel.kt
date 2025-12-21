package com.rmworld.feature.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmworld.core.common.Result
import com.rmworld.core.common.paging.LoadState
import com.rmworld.core.common.paging.LoadStates
import com.rmworld.core.common.paging.LoadType
import com.rmworld.feature.detail.domain.model.Character
import com.rmworld.feature.detail.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val Tag="DetailViewModel"
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

     fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            useCase.invoke(id = characterId).collectLatest { result ->
                when(result){
                    is Result.Loading -> {
                        setLoading(LoadType.REFRESH, LoadState.Loading())

                    }
                    is Result.Error -> {

                    }
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                data = result.data
                            )
                        }
                        setLoading(LoadType.REFRESH, LoadState.NotLoading.Complete)
                        Log.d(Tag, "getCharacterById() called with: result = ${result.data}")
                    }
                }
            }
        }
    }
    private fun setLoading(
        loadType: LoadType,
        loadState: LoadState
    ){
        val newLoadState = uiState.value.loadStates
            .modifyState(loadType,loadState)

        this._uiState.update {
            it.copy(
                loadStates = newLoadState,
                loadState = loadState
            )
        }
    }

}
data class DetailUiState(
    val data: Character?=null,
    val loadStates: LoadStates = LoadStates.IDLE,
    val loadState: LoadState = LoadState.Loading(),
)

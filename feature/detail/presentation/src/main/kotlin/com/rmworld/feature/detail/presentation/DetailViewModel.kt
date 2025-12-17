package com.rmworld.feature.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmworld.core.common.Result
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
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                data = result.data
                            )
                        }
                        Log.d(Tag, "getCharacterById() called with: result = ${result.data}")
                    }
                }
            }
        }
    }

}
data class DetailUiState(
    val data: Character?=null
)

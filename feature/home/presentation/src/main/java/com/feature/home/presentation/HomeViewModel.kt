package com.feature.home.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.home.domain.model.Character
import com.feature.home.domain.repository.HomeRepository
import com.feature.home.domain.usecase.GetAllCharactersUseCase
import com.rmworld.core.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val Tag = "HomeViewModel"
@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val useCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )
    init {
        getAllCharacters()
    }
    private fun getAllCharacters(){
        viewModelScope.launch {
            useCase.invoke().collect{result->
                when(result){
                is Result.Loading -> {
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                }
                is Result.Success -> {
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                    _uiState.update {
                        it.copy(
                            data = result.data
                        )
                    }
                   // _uiState.value = HomeUiState(data = result.data)
                }
                is Result.Error -> {
                    Log.d("Success", "getAllCharacters() called with: result = ${result.exception}")
                }
                }
            }
        }
    }


}

data class HomeUiState(
    val data: List<Character> = emptyList()
)
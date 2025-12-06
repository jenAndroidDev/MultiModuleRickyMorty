package com.feature.home.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.home.domain.model.Character
import com.feature.home.domain.repository.HomeRepository
import com.feature.home.domain.usecase.GetAllCharactersUseCase
import com.rmworld.core.common.Result
import com.rmworld.core.common.paging.LoadState
import com.rmworld.core.common.paging.LoadStates
import com.rmworld.core.common.paging.LoadType
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
    val action:(HomeUiAction)-> Unit
    init {
        getAllCharacters()
        action = {
            onUiAction(it)
        }
    }
    private fun onUiAction(action: HomeUiAction){
        when(action){
            is HomeUiAction.NavigateToDetailScreen->{
                _uiState.update {
                    it.copy(
                        shouldNavToDetailScreen = true,
                        selectedId = action.id
                    )
                }
            }
            is HomeUiAction.ResetNav->{
                _uiState.update {
                    it.copy(
                        shouldNavToDetailScreen = false,
                        selectedId = 0
                    )
                }
            }
        }
    }
    private fun getAllCharacters(
        loadType: LoadType = LoadType.ACTION
    ){
        viewModelScope.launch {
            useCase.invoke().collect{result->
                when(result){
                is Result.Loading -> {
                    setLoading(loadType, LoadState.Loading())
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                }
                is Result.Success -> {
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                    _uiState.update {
                        it.copy(
                            data = result.data
                        )
                    }
                    setLoading(loadType, LoadState.NotLoading.Complete)
                }
                is Result.Error -> {
                    setLoading(loadType, LoadState.Error(result.exception))
                    Log.d("Success", "getAllCharacters() called with: result = ${result.exception}")
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

data class HomeUiState(
    val data: List<Character> = emptyList(),
    val shouldNavToDetailScreen: Boolean  = false,
    val selectedId:Int = 0,
    val loadStates: LoadStates = LoadStates.IDLE,
    val loadState: LoadState = LoadState.Loading(),
)
sealed interface HomeUiAction{
    data class NavigateToDetailScreen(val id: Int): HomeUiAction
    data object ResetNav: HomeUiAction
}
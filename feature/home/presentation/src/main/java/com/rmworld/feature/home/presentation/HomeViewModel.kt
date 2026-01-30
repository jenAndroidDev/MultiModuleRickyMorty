package com.rmworld.feature.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmworld.core.common.Result
import com.rmworld.core.common.apiresult.toUiText
import com.rmworld.core.common.paging.LoadState
import com.rmworld.core.common.paging.LoadStates
import com.rmworld.core.common.paging.LoadType
import com.rmworld.core.common.paging.PagedRequest
import com.rmworld.core.common.ui.UiText
import com.rmworld.feature.home.domain.model.Character
import com.rmworld.feature.home.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

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
    private val scrollState = MutableSharedFlow<HomeUiAction.Scroll>(1)
    private var endOfPagination = false
    private var page:Int = 1
    private var job: Job?=null

    val action:(HomeUiAction)-> Unit
    init {
        scrollState.distinctUntilChanged()
            .onEach {
                if (it.shouldFetchMore && !endOfPagination){
                    createPagedRequest(shouldRefresh = false)
                }
            }.launchIn(viewModelScope)

        createPagedRequest(true)
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
            is HomeUiAction.Scroll -> {
                viewModelScope.launch { scrollState.emit(action) }
            }
        }
    }
    private fun createPagedRequest(shouldRefresh:Boolean){
        if (job?.isActive==true||(!shouldRefresh&&endOfPagination))return
        job?.cancel(CancellationException("Ongoing Api Request"))
        val loadSize = LOAD_SIZE
        val pagedRequest = if (shouldRefresh){
            PagedRequest.create(LoadType.REFRESH, key = page, loadSize = loadSize)
        }else{
            PagedRequest.create(LoadType.APPEND,key = page, loadSize = loadSize)
        }
        getAllCharacters(pagedRequest = pagedRequest)

    }
    private fun getAllCharacters(
        pagedRequest: PagedRequest<Int>,
    ){
        val loadType = if (pagedRequest is PagedRequest.Refresh){
            LoadType.REFRESH

        }else{
            LoadType.APPEND
        }
        viewModelScope.launch {
            useCase.invoke().collect{result->
                when(result){
                is Result.Loading -> {
                    setLoading(loadType, LoadState.Loading())
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                }
                is Result.Success -> {
                    Log.d("Success", "getAllCharacters() called with: result = $result")
                    val tempList = uiState.value.data.toMutableList()
                    tempList.addAll(result.data.data)
                    _uiState.update {
                        it.copy(
                            data = tempList
                        )
                    }
                    endOfPagination = if (result.data.data.isEmpty()){
                        page++
                        true
                    }else{

                        false
                    }
                    setLoading(loadType, LoadState.NotLoading.Complete)
                }
                is Result.Error -> {
                    val uiText = result.exception.toUiText()
                    _uiState.update {
                        it.copy(
                            uiText = uiText
                        )
                    }
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
    val loadStates: LoadStates = LoadStates.IDLE,
    val loadState: LoadState = LoadState.Loading(),
    val data: List<Character> = emptyList(),
    val shouldNavToDetailScreen: Boolean  = false,
    val selectedId:Int = 0,
    val uiText: UiText?=null

)
sealed interface HomeUiAction{
    data class NavigateToDetailScreen(val id: Int): HomeUiAction
    data object ResetNav: HomeUiAction
    data class Scroll(
        val visibleItemCount:Int,
        val lastVisibleItemPosition: Int,
        val totalItemCount: Int
    ): HomeUiAction
}
private const val LOAD_SIZE = 10
private const val DEFAULT_VISIBLE_THRESHOLD = 10

private val HomeUiAction.Scroll.shouldFetchMore: Boolean
    get() =  visibleItemCount+lastVisibleItemPosition+ DEFAULT_VISIBLE_THRESHOLD >=totalItemCount
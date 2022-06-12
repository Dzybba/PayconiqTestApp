package com.example.payconiqtestapp.searchlist.presentation

import androidx.core.view.isVisible
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.searchlist.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.data.repository.SearchUserRepository
import com.example.payconiqtestapp.searchlist.presentation.mapper.SearchUserDtoToUserMapper
import com.example.payconiqtestapp.searchlist.presentation.model.User
import com.example.payconiqtestapp.searchlist.presentation.model.ViewModelState
import com.example.payconiqtestapp.searchlist.presentation.model.ViewState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val MAX_PAGE_SIZE = 30
private const val DEBOUNCE_TIMEOUT = 500L

class SearchUsersViewModel(
    private val repository: SearchUserRepository,
    private val userMapper: SearchUserDtoToUserMapper,
) : ViewModel() {

    private val pagingConfig = PagingConfig(
        pageSize = MAX_PAGE_SIZE,
        enablePlaceholders = false
    )

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _viewModelState = MutableStateFlow<ViewModelState>(ViewModelState.EnterNameState)
    val viewModelState: StateFlow<ViewModelState> = _viewModelState.asStateFlow()

    val pagerState: StateFlow<PagingData<User>> = query.debounce(DEBOUNCE_TIMEOUT)
        .map { newPager(it) }
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .map { pagingData -> pagingData.map { userMapper.map(it) } }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: String): Pager<Int, SearchUserDto> {
        return Pager(pagingConfig) {
            repository.getSourceForSearch(query)
        }
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }

    fun onViewStateChanged(viewState: ViewState) {
        _viewModelState.tryEmit(getViewModelState(viewState))
    }

    private fun getViewModelState(viewState: ViewState): ViewModelState {
        return when (viewState.loadingState) {
            is LoadState.NotLoading -> {
                getViewModelStateFromNotLoadedState(viewState)
            }
            is LoadState.Loading -> {
                if (viewState.itemCount > 0) {
                    ViewModelState.LoadedState
                } else {
                    ViewModelState.LoadingState
                }
            }
            is LoadState.Error -> {
                ViewModelState.ErrorState
            }
        }
    }

    private fun getViewModelStateFromNotLoadedState(viewState: ViewState): ViewModelState {
        return if (viewState.itemCount > 0) {
            ViewModelState.LoadedState
        } else {
            if (viewState.query.isEmpty()) {
                ViewModelState.EnterNameState
            } else {
                ViewModelState.NotFoundState
            }
        }
    }

    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val repository: SearchUserRepository,
        private val userMapper: SearchUserDtoToUserMapper,
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return SearchUsersViewModel(
                repository,
                userMapper
            ) as T
        }
    }
}
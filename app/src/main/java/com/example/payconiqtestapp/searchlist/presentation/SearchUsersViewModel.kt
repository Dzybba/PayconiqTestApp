package com.example.payconiqtestapp.searchlist.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.searchlist.data.repository.SearchUserRepository
import com.example.payconiqtestapp.searchlist.presentation.mapper.SearchResultToUserMapper
import com.example.payconiqtestapp.searchlist.presentation.model.ViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUsersViewModel(
    private val repository: SearchUserRepository,
    private val userMapper: SearchResultToUserMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewModelState>(ViewModelState.LoadingState)
    val uiState: StateFlow<ViewModelState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ViewModelState.LoadingState
            val result = repository.search(
                // todo pass real search data
                query = "den",
                page = 1,
                perPage = 30
            ).getOrNull()
            _uiState.value = if (result != null) {
                ViewModelState.LoadedState(userMapper.map(result))
            } else {
                ViewModelState.ErrorState
            }
        }
    }


    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val repository: SearchUserRepository,
        private val userMapper: SearchResultToUserMapper
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
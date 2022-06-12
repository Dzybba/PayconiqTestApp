package com.example.payconiqtestapp.searchlist.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.searchlist.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.data.repository.SearchUserRepository
import com.example.payconiqtestapp.searchlist.presentation.mapper.SearchUserDtoToUserMapper
import com.example.payconiqtestapp.searchlist.presentation.model.User
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val MAX_PAGE_SIZE = 30

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

    val pagerState: StateFlow<PagingData<User>> = query.map { newPager(it) }
        .flatMapLatest { pager -> pager.flow }
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
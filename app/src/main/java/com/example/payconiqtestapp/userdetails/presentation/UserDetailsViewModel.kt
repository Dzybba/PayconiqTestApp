package com.example.payconiqtestapp.userdetails.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.userdetails.data.repository.UserDetailsRepository
import com.example.payconiqtestapp.userdetails.presentation.model.ViewModelState
import com.example.payconiqtestapp.userdetails.presentation.view.UserDetailsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel(
    private val login: String,
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewModelState>(ViewModelState.Loading)
    val uiState: StateFlow<ViewModelState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ViewModelState.Loading
            val result = userDetailsRepository.getUserDetails(login)
            _uiState.value = if (result.isSuccessful) {
                val response = checkNotNull(result.body())
                ViewModelState.Loaded(
                    UserDetailsModel(
                        login = response.login,
                        avatarUrl = response.avatarUrl,
                        name = response.name,
                        company = response.company,
                        location = response.location,
                        email = response.email,
                        bio = response.bio,
                        twitterUsername = response.twitterUsername,
                        publicRepos = response.publicRepos,
                        publicGists = response.publicGists,
                        followers = response.followers,
                        following = response.following
                    )
                )
            } else {
                ViewModelState.Error
            }
        }
    }

    fun reload() {
        loadData()
    }

    class Factory
    @Inject constructor(
        owner: SavedStateRegistryOwner,
        private val login: String,
        private val userDetailsRepository: UserDetailsRepository
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return UserDetailsViewModel(
                login,
                userDetailsRepository
            ) as T
        }
    }
}
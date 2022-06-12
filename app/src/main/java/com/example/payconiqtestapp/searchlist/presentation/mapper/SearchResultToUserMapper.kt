package com.example.payconiqtestapp.searchlist.presentation.mapper

import com.example.payconiqtestapp.searchlist.data.dto.SearchResultResponse
import com.example.payconiqtestapp.searchlist.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.presentation.model.ViewModelState
import javax.inject.Inject

class SearchResultToUserMapper
@Inject constructor() {

    fun map(response: SearchResultResponse): List<ViewModelState.LoadedState.User> {
        return response.items.map { mapUser(it) }
    }

    private fun mapUser(dto: SearchUserDto): ViewModelState.LoadedState.User {
        return ViewModelState.LoadedState.User(
            dto.login,
            dto.avatarUrl
        )
    }
}
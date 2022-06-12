package com.example.payconiqtestapp.searchlist.presentation.mapper

import com.example.payconiqtestapp.searchlist.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.presentation.model.User
import javax.inject.Inject

class SearchUserDtoToUserMapper
@Inject constructor() {

    fun map(dto: SearchUserDto): User {
        return User(
            dto.login,
            dto.avatarUrl
        )
    }
}
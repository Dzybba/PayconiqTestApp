package com.example.payconiqtestapp.searchlist.presentation.mapper

import com.example.payconiqtestapp.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.presentation.model.User
import javax.inject.Inject

class SearchUserDtoToUserMapper
@Inject constructor() {

    fun map(dto: SearchUserDto): User {
        return User(
            id = dto.id,
            name = dto.login,
            avatarUrl = dto.avatarUrl,
            score = dto.score
        )
    }
}
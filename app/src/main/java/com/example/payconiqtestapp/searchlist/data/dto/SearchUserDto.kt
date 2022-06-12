package com.example.payconiqtestapp.searchlist.data.dto

import com.squareup.moshi.Json

data class SearchUserDto(
    val id: Int,
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val url: String
)

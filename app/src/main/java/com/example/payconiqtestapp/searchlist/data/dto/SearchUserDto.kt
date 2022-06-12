package com.example.payconiqtestapp.searchlist.data.dto

import com.squareup.moshi.Json

data class SearchUserDto(
    val id: Int,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val url: String
)

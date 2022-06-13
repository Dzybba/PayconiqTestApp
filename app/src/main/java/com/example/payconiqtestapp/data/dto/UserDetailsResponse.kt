package com.example.payconiqtestapp.data.dto

import com.squareup.moshi.Json

class UserDetailsResponse(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?
)
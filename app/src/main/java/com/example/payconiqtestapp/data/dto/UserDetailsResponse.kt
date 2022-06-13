package com.example.payconiqtestapp.data.dto

import com.squareup.moshi.Json

data class UserDetailsResponse(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String,
    val bio: String,
    @Json(name = "twitter_username")
    val twitterUsername: String,
    @Json(name = "public_repos")
    val publicRepos: Int,
    @Json(name = "public_gists")
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)
package com.example.payconiqtestapp.userdetails.presentation.view

class UserDetailsModel(
    val login: String,
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitterUsername: String?,
    val publicRepos: Int?,
    val publicGists: Int?,
    val followers: Int?,
    val following: Int?
)
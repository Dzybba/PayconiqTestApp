package com.example.payconiqtestapp.userdetails.data.repository

import com.example.payconiqtestapp.data.service.GithubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsRepository
@Inject constructor(
    private val githubApiService: GithubApiService
) {

    suspend fun getUserDetails(userName: String) = withContext(Dispatchers.IO) {
        return@withContext githubApiService.getUserDetails(userName)
    }
}
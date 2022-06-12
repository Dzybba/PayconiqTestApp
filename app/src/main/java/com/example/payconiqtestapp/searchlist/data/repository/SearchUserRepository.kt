package com.example.payconiqtestapp.searchlist.data.repository

import com.example.payconiqtestapp.searchlist.data.service.GithubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUserRepository
@Inject constructor(
    private val githubApiService: GithubApiService
) {

    suspend fun search(
        query: String,
        page: Int,
        perPage: Int
    ) = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = githubApiService.search(query, page, perPage)
            Result.success(result)
        } catch (th: Throwable) {
            Result.failure(th)
        }
    }
}
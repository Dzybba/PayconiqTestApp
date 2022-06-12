package com.example.payconiqtestapp.searchlist.data.service

import com.example.payconiqtestapp.searchlist.data.dto.SearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchResultResponse>
}
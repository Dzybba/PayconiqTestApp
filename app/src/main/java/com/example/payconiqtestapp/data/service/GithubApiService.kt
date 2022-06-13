package com.example.payconiqtestapp.data.service

import com.example.payconiqtestapp.data.dto.SearchResultResponse
import com.example.payconiqtestapp.data.dto.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchResultResponse>

    @GET("/users/{username}")
    suspend fun getUserDetails(
        @Path("username") userName: String
    ) : Response<UserDetailsResponse>
}
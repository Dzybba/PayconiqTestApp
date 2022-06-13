package com.example.payconiqtestapp.data.dto

import com.squareup.moshi.Json

data class SearchResultResponse(
    @Json(name = "total_count")
    val totalCount: String,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<SearchUserDto>
)

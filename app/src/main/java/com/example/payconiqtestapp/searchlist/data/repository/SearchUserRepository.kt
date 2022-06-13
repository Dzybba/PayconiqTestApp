package com.example.payconiqtestapp.searchlist.data.repository

import androidx.paging.PagingSource
import com.example.payconiqtestapp.data.dto.SearchUserDto
import javax.inject.Inject

class SearchUserRepository
@Inject constructor(
    private val searchUserPagingSourceFactory: SearchUserPagingSource.Factory
) {

    fun getSourceForSearch(query: String): PagingSource<Int, SearchUserDto> {
        return searchUserPagingSourceFactory.create(query)
    }
}
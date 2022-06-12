package com.example.payconiqtestapp.searchlist.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.payconiqtestapp.searchlist.data.dto.SearchResultResponse
import com.example.payconiqtestapp.searchlist.data.dto.SearchUserDto
import com.example.payconiqtestapp.searchlist.data.service.GithubApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class SearchUserPagingSource
@AssistedInject constructor(
    private val service: GithubApiService,
    @Assisted private val query: String
) : PagingSource<Int, SearchUserDto>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchUserDto> {
        if (query.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        val page = params.key ?: 1
        val response = service.search(query, page, params.loadSize)
        return if (response.isSuccessful) {
            getPage(page, params.loadSize, checkNotNull(response.body()))
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    private fun getPage(
        currentPage: Int,
        pageSize: Int,
        response: SearchResultResponse
    ): LoadResult<Int, SearchUserDto> {
        val nextKey = if (pageSize >= response.items.size) {
           currentPage + 1
        } else {
            null
        }
        return LoadResult.Page(
            data = response.items,
            prevKey = null,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, SearchUserDto>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.nextKey?.minus(1)
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted query: String): SearchUserPagingSource
    }
}
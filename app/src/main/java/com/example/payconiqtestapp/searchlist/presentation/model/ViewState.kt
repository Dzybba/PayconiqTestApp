package com.example.payconiqtestapp.searchlist.presentation.model

import androidx.paging.LoadState

class ViewState(
    val query: String,
    val loadingState: LoadState,
    val itemCount: Int
)
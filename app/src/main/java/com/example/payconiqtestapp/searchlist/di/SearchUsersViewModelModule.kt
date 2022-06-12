package com.example.payconiqtestapp.searchlist.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.example.payconiqtestapp.searchlist.presentation.SearchUsersViewModel
import dagger.Binds
import dagger.Module

@Module
interface SearchUsersViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: SearchUsersViewModel.Factory): AbstractSavedStateViewModelFactory
}
package com.example.payconiqtestapp.userdetails.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.example.payconiqtestapp.searchlist.presentation.SearchUsersViewModel
import com.example.payconiqtestapp.userdetails.presentation.UserDetailsViewModel
import dagger.Binds
import dagger.Module

@Module
interface UserDetailsViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: UserDetailsViewModel.Factory): AbstractSavedStateViewModelFactory
}
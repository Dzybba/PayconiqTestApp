package com.example.payconiqtestapp.searchlist.di

import com.example.payconiqtestapp.searchlist.data.service.GithubApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object GithubApiServiceModule {

    @Provides
    fun provideService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }
}
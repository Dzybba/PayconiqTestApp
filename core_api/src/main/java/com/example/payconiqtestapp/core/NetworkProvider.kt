package com.example.payconiqtestapp.core

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}
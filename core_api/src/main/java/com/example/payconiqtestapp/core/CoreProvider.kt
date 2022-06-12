package com.example.payconiqtestapp.core

import android.content.Context

interface CoreProvider {

    @ApplicationContext
    fun applicationContext(): Context
}
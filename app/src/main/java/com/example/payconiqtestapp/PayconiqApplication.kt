package com.example.payconiqtestapp

import android.app.Application
import com.example.payconiqtestapp.core.ProvidersAccumulator
import com.example.payconiqtestapp.core.ProvidersHolder
import com.example.payconiqtestapp.di.ApplicationComponent

class PayconiqApplication : Application(), ProvidersHolder {

    companion object {

        private var providersAccumulator: ProvidersAccumulator? = null
    }

    override fun getProvidersAccumulator(): ProvidersAccumulator {
        return providersAccumulator ?: ApplicationComponent.create(this).also {
            providersAccumulator = it
        }
    }
}

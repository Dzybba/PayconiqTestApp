package com.example.payconiqtestapp.di

import android.content.Context
import com.example.payconiqtestapp.core.CoreProvider
import com.example.payconiqtestapp.core.NetworkProvider
import com.example.payconiqtestapp.core.ProvidersAccumulator
import com.example.payconiqtestapp.coreimpl.di.CoreComponent
import com.example.payconiqtestapp.coreimpl.di.NetworkComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        CoreProvider::class,
        NetworkProvider::class
    ]
)
@Singleton
interface ApplicationComponent: ProvidersAccumulator {

    companion object {

        fun create(context: Context): ProvidersAccumulator {
            val coreProvider = CoreComponent.create(context)
            val networkProvider = NetworkComponent.create()
            return DaggerApplicationComponent.factory()
                .create(
                    coreProvider,
                    networkProvider
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            coreProvider: CoreProvider,
            networkProvider: NetworkProvider,
        ): ApplicationComponent
    }
}
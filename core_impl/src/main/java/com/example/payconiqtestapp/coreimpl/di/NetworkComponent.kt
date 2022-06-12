package com.example.payconiqtestapp.coreimpl.di

import com.example.payconiqtestapp.core.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider {

    companion object {

        fun create(): NetworkComponent {
            return DaggerNetworkComponent.create()
        }
    }
}

package com.example.payconiqtestapp.coreimpl.di

import android.content.Context
import com.example.payconiqtestapp.core.ApplicationContext
import com.example.payconiqtestapp.core.CoreProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface CoreComponent : CoreProvider {

    companion object {

        fun create(context: Context): CoreComponent {
            return DaggerCoreComponent.factory()
                .create(context)
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance @ApplicationContext context: Context
        ): CoreComponent
    }
}

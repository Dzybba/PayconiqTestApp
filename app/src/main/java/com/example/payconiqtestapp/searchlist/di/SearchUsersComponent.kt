package com.example.payconiqtestapp.searchlist.di

import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.core.ProvidersAccumulator
import com.example.payconiqtestapp.searchlist.presentation.SearchUsersFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ProvidersAccumulator::class],
    modules = [
        GithubApiServiceModule::class,
        SearchUsersViewModelModule::class
    ]
)
interface SearchUsersComponent {

    companion object {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            savedStateRegistryOwner: SavedStateRegistryOwner
        ): SearchUsersComponent {
            return DaggerSearchUsersComponent.factory()
                .create(
                    providersAccumulator,
                    savedStateRegistryOwner
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner
        ): SearchUsersComponent
    }

    fun inject(fragment: SearchUsersFragment)
}
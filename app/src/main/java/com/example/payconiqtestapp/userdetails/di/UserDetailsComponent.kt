package com.example.payconiqtestapp.userdetails.di

import androidx.savedstate.SavedStateRegistryOwner
import com.example.payconiqtestapp.core.ProvidersAccumulator
import com.example.payconiqtestapp.di.GithubApiServiceModule
import com.example.payconiqtestapp.searchlist.presentation.SearchUsersFragment
import com.example.payconiqtestapp.userdetails.presentation.UserDetailsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ProvidersAccumulator::class],
    modules = [
        GithubApiServiceModule::class,
        UserDetailsViewModelModule::class
    ]
)
interface UserDetailsComponent {

    companion object {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            login: String,
            savedStateRegistryOwner: SavedStateRegistryOwner
        ): UserDetailsComponent {
            return DaggerUserDetailsComponent.factory()
                .create(
                    providersAccumulator,
                    login,
                    savedStateRegistryOwner
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            providersAccumulator: ProvidersAccumulator,
            @BindsInstance login: String,
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner
        ): UserDetailsComponent
    }

    fun inject(fragment: UserDetailsFragment)
}
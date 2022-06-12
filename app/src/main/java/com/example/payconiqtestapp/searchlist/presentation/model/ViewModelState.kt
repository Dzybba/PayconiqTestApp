package com.example.payconiqtestapp.searchlist.presentation.model

sealed class ViewModelState {

    object LoadingState : ViewModelState()

    object ErrorState : ViewModelState()

    class LoadedState(
        val users: List<User>
    ) : ViewModelState() {

        class User(
            val name: String,
            val avatarUrl: String
        )
    }
}
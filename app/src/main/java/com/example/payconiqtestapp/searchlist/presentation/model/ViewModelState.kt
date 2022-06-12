package com.example.payconiqtestapp.searchlist.presentation.model

sealed class ViewModelState {

    object EnterNameState : ViewModelState()

    object NotFoundState : ViewModelState()

    object LoadingState : ViewModelState()

    object LoadedState : ViewModelState()

    object ErrorState : ViewModelState()

}
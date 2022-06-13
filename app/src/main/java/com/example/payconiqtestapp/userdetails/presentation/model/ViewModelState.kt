package com.example.payconiqtestapp.userdetails.presentation.model

import com.example.payconiqtestapp.userdetails.presentation.view.UserDetailsModel

sealed class ViewModelState {

    object Loading : ViewModelState()

    object Error : ViewModelState()

    class Loaded(
        val user: UserDetailsModel
    ) : ViewModelState()
}

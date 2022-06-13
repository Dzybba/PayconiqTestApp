package com.example.payconiqtestapp.userdetails.presentation

import android.os.Bundle
import androidx.core.os.bundleOf

private const val LOGIN_KEY = "login_key"

object UserDetailsFragmentContract {

    fun getBundle(login: String): Bundle {
        return bundleOf(LOGIN_KEY to login)
    }

    fun getLoginFromBundle(bundle: Bundle): String {
        return bundle.getString(LOGIN_KEY, "")
    }
}
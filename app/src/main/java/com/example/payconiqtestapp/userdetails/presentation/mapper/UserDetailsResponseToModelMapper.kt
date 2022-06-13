package com.example.payconiqtestapp.userdetails.presentation.mapper

import android.content.Context
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.core.ApplicationContext
import com.example.payconiqtestapp.data.dto.UserDetailsResponse
import com.example.payconiqtestapp.userdetails.presentation.view.UserDetailsModel
import com.example.payconiqtestapp.userdetails.presentation.view.adapter.UserDetailModel
import javax.inject.Inject

class UserDetailsResponseToModelMapper
@Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun map(response: UserDetailsResponse): UserDetailsModel {
        val details = mutableListOf<UserDetailModel>()
        details.add(UserDetailModel(context.getString(R.string.user_login), response.login))
        if (response.name != null) {
            details.add(UserDetailModel(context.getString(R.string.user_name), response.name))
        }
        if (response.company != null) {
            details.add(UserDetailModel(context.getString(R.string.user_company), response.company))
        }
        if (response.location != null) {
            details.add(UserDetailModel(context.getString(R.string.user_location), response.location))
        }
        if (response.email != null) {
            details.add(UserDetailModel(context.getString(R.string.user_email), response.email))
        }
        if (response.bio != null) {
            details.add(UserDetailModel(null, response.bio))
        }
        return UserDetailsModel(response.avatarUrl, details)
    }
}
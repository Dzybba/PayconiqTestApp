package com.example.payconiqtestapp.userdetails.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.payconiqtestapp.searchlist.presentation.model.User

class UserDetailsDiffItemCallback : DiffUtil.ItemCallback<UserDetailModel>() {

    override fun areItemsTheSame(oldItem: UserDetailModel, newItem: UserDetailModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserDetailModel, newItem: UserDetailModel): Boolean {
        return oldItem == newItem
    }
}
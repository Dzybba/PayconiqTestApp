package com.example.payconiqtestapp.searchlist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.payconiqtestapp.searchlist.presentation.model.User

class UserDiffItemCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
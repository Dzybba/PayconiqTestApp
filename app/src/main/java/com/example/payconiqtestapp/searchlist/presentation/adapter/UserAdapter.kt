package com.example.payconiqtestapp.searchlist.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.searchlist.presentation.model.User

class UserAdapter(
    context: Context,
    private val onClickAction: (user: User?) -> Unit
) : PagingDataAdapter<User, UserViewHolder>(UserDiffItemCallback()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = layoutInflater.inflate(
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(view, onClickAction)
    }
}
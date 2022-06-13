package com.example.payconiqtestapp.userdetails.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.payconiqtestapp.R

class UserDetailsAdapter(
    context: Context
) : ListAdapter<UserDetailModel, UserDetailViewHolder>(UserDetailsDiffItemCallback()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
        val view = layoutInflater.inflate(
            R.layout.item_user_detail,
            parent,
            false
        )
        return UserDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
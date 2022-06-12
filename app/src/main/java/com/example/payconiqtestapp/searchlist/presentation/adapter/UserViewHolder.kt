package com.example.payconiqtestapp.searchlist.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.payconiqtestapp.searchlist.presentation.model.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User?) {
        (itemView as? UserView)?.populate(user)
    }
}
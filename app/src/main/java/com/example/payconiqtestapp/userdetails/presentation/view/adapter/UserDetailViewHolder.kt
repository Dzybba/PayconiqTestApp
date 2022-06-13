package com.example.payconiqtestapp.userdetails.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class UserDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: UserDetailModel) {
        (itemView as? UserDetailView)?.populate(model)
    }
}
package com.example.payconiqtestapp.searchlist.presentation.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.databinding.ItemUserContentBinding
import com.example.payconiqtestapp.searchlist.presentation.model.User
import com.google.android.material.card.MaterialCardView

class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ItemUserContentBinding.bind(this) }

    init {
        inflate(context, R.layout.item_user_content, this)
    }

    fun populate(user: User?) {
        // todo
        binding.name.text = user?.name
    }
}
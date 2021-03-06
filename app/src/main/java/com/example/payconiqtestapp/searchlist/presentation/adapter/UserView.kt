package com.example.payconiqtestapp.searchlist.presentation.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.databinding.ItemUserContentBinding
import com.example.payconiqtestapp.searchlist.presentation.model.User
import com.example.payconiqtestapp.utils.imageview.showCircleImage
import com.google.android.material.card.MaterialCardView

class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ItemUserContentBinding.bind(this) }

    var clickAction: () -> Unit = {}

    init {
        inflate(context, R.layout.item_user_content, this)
        setOnClickListener { clickAction() }
    }

    fun populate(user: User?) {
        user?.avatarUrl?.let { binding.avatar.showCircleImage(it) }
        binding.name.text = user?.name
        binding.scrore.text = resources.getString(R.string.user_score, user?.score ?: 0)
    }
}
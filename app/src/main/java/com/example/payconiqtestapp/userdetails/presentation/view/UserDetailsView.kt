package com.example.payconiqtestapp.userdetails.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import androidx.annotation.AttrRes
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.databinding.UserDetailsBinding
import com.example.payconiqtestapp.utils.imageview.showCircleImage

class UserDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { UserDetailsBinding.bind(this) }

    init {
        inflate(context, R.layout.user_details, this)
    }

    fun populate(model: UserDetailsModel) {
        binding.avatar.showCircleImage(model.avatarUrl)
        binding.nick.text = model.login
    }
}
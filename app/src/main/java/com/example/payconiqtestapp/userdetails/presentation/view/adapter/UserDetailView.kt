package com.example.payconiqtestapp.userdetails.presentation.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.view.isVisible
import com.example.payconiqtestapp.databinding.ItemUserDetailBinding

class UserDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ItemUserDetailBinding.bind(this) }

    fun populate(model: UserDetailModel) {
        binding.userDetailTitle.isVisible = model.title != null
        binding.userDetailTitle.text = model.title
        binding.userDetailText.text = model.text
    }
}
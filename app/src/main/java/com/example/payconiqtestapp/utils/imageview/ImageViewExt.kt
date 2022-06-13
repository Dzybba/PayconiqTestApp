package com.example.payconiqtestapp.utils.imageview

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.utils.glide.GlideApp

private val defaultStrategy = DiskCacheStrategy.ALL

fun ImageView.showCircleImage(url: String) {
    GlideApp.with(context)
        .load(Uri.parse(url))
        .diskCacheStrategy(defaultStrategy)
        .placeholder(getPlaceholderDrawable(context))
        .transform(CenterCrop(), CircleCrop())
        .into(this)
}

private fun getPlaceholderDrawable(context: Context): ShapeDrawable {
    val shape = OvalShape()
    val drawable = ShapeDrawable(shape)
    drawable.paint.color = ContextCompat.getColor(context, R.color.teal_200)
    return drawable
}
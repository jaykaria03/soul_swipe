package com.discover.soulswipe.core.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation


fun ImageView.loadImageWithBlur(
    url: Any?,
    placeholder: Drawable? = null,
    listener: RequestListener<Drawable>? = null,
    skipCache: Boolean = true,
) {
    try {
        Glide.with(this.context)
            .load(url)
            .apply(bitmapTransform(BlurTransformation(25, 3)))
            .timeout(180000)
            .placeholder(placeholder)
            .addListener(listener)
            .skipMemoryCache(skipCache)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

fun ImageView.loadImage(
    url: Any?,
    placeholder: Drawable? = null,
    listener: RequestListener<Drawable>? = null,
    skipCache: Boolean = true,
) {
    try {
        Glide.with(this.context)
            .load(url)
            .timeout(180000)
            .placeholder(placeholder)
            .addListener(listener)
            .skipMemoryCache(skipCache)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
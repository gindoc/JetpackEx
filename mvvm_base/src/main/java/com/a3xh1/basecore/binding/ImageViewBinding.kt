package com.a3xh1.basecore.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Author: GIndoc on 2019/3/11.
 * FOR   :
 */

@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, url: String?) =
    Glide.with(imageView.context).load(url).into(imageView)

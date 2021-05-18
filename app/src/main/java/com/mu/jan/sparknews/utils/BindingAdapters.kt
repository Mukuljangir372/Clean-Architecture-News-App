package com.mu.jan.sparknews.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageUsingUrl")
fun loadImageUsingUrl(view: ImageView,url: String? = null){
    if(url!=null) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

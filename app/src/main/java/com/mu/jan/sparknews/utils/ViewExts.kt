package com.mu.jan.sparknews.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.toVisible(){
    visibility = View.VISIBLE
}
fun View.toNotVisible(){
    visibility = View.GONE
}
fun Context.showToast(text: String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

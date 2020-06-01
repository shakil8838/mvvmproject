package xyz.xandsoft.mvvmproject.utills

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.showToast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}
package com.brunocortez.android19mob.ui.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showMessage(message: String? = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.showLoading() {
    // TODO: show loading
}
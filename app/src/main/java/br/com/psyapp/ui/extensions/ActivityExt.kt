package br.com.psyapp.ui.extensions

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

fun AppCompatActivity.showMessage(message: String? = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showLoading() {
    // TODO: show loading
}

fun AppCompatActivity.adjustSystemLayout() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

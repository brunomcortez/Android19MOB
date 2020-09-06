package br.com.psyapp.ui.extensions

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showMessage(message: String? = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showLoading() {
    // TODO: show loading
}

fun AppCompatActivity.adjustSystemLayout() {
    window.apply {
        decorView.systemUiVisibility = decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}

package com.brunocortez.android19mob.ui.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showMessage(message: String? = "") {
    activity?.let {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).showMessage(message)
        }
    }
}
package br.com.psyapp.lib.emotions.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    activity?.let { if (it is AppCompatActivity) { it.hideKeyboard() } }
}
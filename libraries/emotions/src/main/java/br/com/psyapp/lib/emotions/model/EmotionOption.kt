package br.com.psyapp.lib.emotions.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class EmotionOption(
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
    var selected: Boolean = false
)

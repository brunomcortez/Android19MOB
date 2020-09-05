package br.com.psyapp.utils.featuretoogle

import android.content.Context

interface FeatureToggleListener {
    fun onEnabled()
    fun onInvisible()
    fun onDisabled(clickListener: (Context) -> Unit)
}
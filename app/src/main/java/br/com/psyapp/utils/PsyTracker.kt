package br.com.psyapp.utils

import android.app.Activity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object PsyTracker {
    fun trackEvent(activity: Activity, eventKey: String, bundle: Bundle) {
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity)
        mFirebaseAnalytics.logEvent(eventKey, bundle)
    }
}

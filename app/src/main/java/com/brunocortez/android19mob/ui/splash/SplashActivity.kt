package com.brunocortez.android19mob.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.brunocortez.android19mob.MainActivity
import com.brunocortez.android19mob.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
        startAnimation()
    }

    private fun startAnimation() {
        val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)
        img.startAnimation(pulse)
    }
}
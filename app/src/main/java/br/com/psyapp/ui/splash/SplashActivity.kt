package br.com.psyapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.psyapp.MainActivity
import br.com.psyapp.R
import br.com.psyapp.ui.extensions.adjustSystemLayout
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adjustSystemLayout()

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

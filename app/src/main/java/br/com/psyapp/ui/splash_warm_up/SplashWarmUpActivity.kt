package br.com.psyapp.ui.splash_warm_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.psyapp.ui.splash.SplashActivity

class SplashWarmUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, SplashActivity::class.java).also {
            startActivity(it)

            finish()
        }
    }
}

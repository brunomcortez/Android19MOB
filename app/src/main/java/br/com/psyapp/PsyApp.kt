package br.com.psyapp

import android.app.Application
import br.com.psyapp.lib.emotions.Emotions
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PsyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PsyApp)
            modules(listOf())
        }

        Emotions.setup()
    }

}

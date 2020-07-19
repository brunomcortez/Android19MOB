package br.com.psyapp.lib.emotions.config

import androidx.room.Room
import br.com.psyapp.lib.emotions.persistence.EmotionsDatabase
import br.com.psyapp.lib.emotions.ui.emotions_map.EmotionsMapAdapter
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(get(), EmotionsDatabase::class.java, "Emotions.db")
            .build()
    }
}

val adaptersModule = module {
    factory { EmotionsMapAdapter() }
}

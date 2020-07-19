package br.com.psyapp.lib.emotions.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Emotion::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverters::class)
abstract class EmotionsDatabase : RoomDatabase() {

    abstract fun emotionDao(): EmotionDao

}

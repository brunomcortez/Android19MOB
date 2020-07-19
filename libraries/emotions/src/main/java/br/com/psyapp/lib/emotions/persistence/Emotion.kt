package br.com.psyapp.lib.emotions.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "emotions")
data class Emotion(
    @ColumnInfo(name = "emotion_kind")
    val kind: String,
    @ColumnInfo(name = "emotion_detail")
    val detail: String?,
    @ColumnInfo(name = "emotion_registered")
    val registered: Date,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "emotion_id")
    val id: String = UUID.randomUUID().toString()
)

package br.com.psyapp.lib.emotions.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "emotions")
data class Emotion(
    @ColumnInfo(name = "emotion_kind")
    var kind: Int,
    @ColumnInfo(name = "emotion_detail")
    var detail: String?,
    @ColumnInfo(name = "emotion_registered")
    val registered: Date,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "emotion_id")
    val id: String = UUID.randomUUID().toString()
) : Serializable

package br.com.psyapp.lib.emotions.persistence

import androidx.room.TypeConverter
import java.util.*

class DataConverters {
    @TypeConverter
    fun toDate(value: Long?): Date? =
        if (value != null) Date(value) else null

    @TypeConverter
    fun fromDate(value: Date?): Long? = value?.time
}

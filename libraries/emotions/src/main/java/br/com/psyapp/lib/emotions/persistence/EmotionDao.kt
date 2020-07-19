package br.com.psyapp.lib.emotions.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*

@Dao
interface EmotionDao {

    @Query("SELECT * FROM emotions WHERE emotion_registered BETWEEN :start AND :end ORDER BY emotion_registered ASC")
    fun getEmotionByRegisterBetween(start: Date, end: Date): Flowable<Emotion>

    @Query("SELECT * FROM emotions ORDER BY emotion_registered ASC")
    fun getEmotions(): Observable<List<Emotion>>

    @Insert
    fun insertEmotion(emotion: Emotion): Completable

    @Update
    fun updateEmotion(emotion: Emotion): Completable

    @Delete
    fun removeEmotion(emotion: Emotion): Completable

}

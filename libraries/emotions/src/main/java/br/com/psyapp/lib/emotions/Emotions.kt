package br.com.psyapp.lib.emotions

import br.com.psyapp.lib.emotions.config.adaptersModule
import br.com.psyapp.lib.emotions.config.dataSourceModule
import br.com.psyapp.lib.emotions.persistence.Emotion
import br.com.psyapp.lib.emotions.persistence.EmotionsDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import java.util.*

class Emotions : KoinComponent {

    private val database: EmotionsDatabase by inject()

    fun init() = loadKoinModules(listOf(dataSourceModule, adaptersModule))

    fun listEmotions(): Observable<List<Emotion>> = database.emotionDao().getEmotions()

    fun listEmotionsFromWeek(): Flowable<Emotion> {
        val weekFirstDay = Calendar.getInstance()
        val weekLastDay = Calendar.getInstance()

        weekFirstDay.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        weekLastDay.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

        return database.emotionDao().getEmotionByRegisterBetween(weekFirstDay.time, weekLastDay.time)
    }

    fun registerEmotion(kind: String, detail: String?, date: Date? = null): Completable {
        val registered = if (date != null) java.sql.Date(date.time) else java.sql.Date(Calendar.getInstance().timeInMillis)
        val emotion = Emotion(kind, detail, registered)

        return database.emotionDao().insertEmotion(emotion)
    }

    fun alterEmotion(emotion: Emotion): Completable {
        return database.emotionDao().updateEmotion(emotion)
    }

    fun removeEmotion(emotion: Emotion): Completable {
        return database.emotionDao().removeEmotion(emotion)
    }

    companion object {
        @Volatile private var INSTANCE: Emotions? = null

        fun getInstance() = INSTANCE ?: Emotions().also { INSTANCE = it }
    }
}

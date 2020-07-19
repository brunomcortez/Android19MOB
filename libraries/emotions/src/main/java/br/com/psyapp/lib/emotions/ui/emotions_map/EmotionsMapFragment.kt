package br.com.psyapp.lib.emotions.ui.emotions_map

import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_emotions_map.*
import org.koin.android.ext.android.inject
import java.util.*

class EmotionsMapFragment : Fragment() {

    val constraintOrigin = ConstraintSet()
    val constraintDestination = ConstraintSet()

    val emotionsMapAdapter: EmotionsMapAdapter by inject()

    val invalidateTimer = Thread(Runnable {
        while (true) {
            v_add.post {
                v_add.invalidate()
            }

            Thread.sleep(13)
        }
    })

    private val root: ConstraintLayout by lazy {
        view?.rootView as ConstraintLayout
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_emotions_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constraintOrigin.clone(root)
        constraintDestination.clone(context, R.layout.fragment_emotions_map_alt)

        rv_emotions.adapter = emotionsMapAdapter
        rv_emotions.layoutManager = LinearLayoutManager(context)

        btn_new.setOnClickListener {
            newEmotion()
        }

        btn_add.setOnClickListener {
            addEmotion()
        }

        btn_cancel.setOnClickListener {
            closeEmotion()
        }

        Emotions.getInstance().listEmotions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                emotionsMapAdapter.registers = it

                emotionsMapAdapter.notifyDataSetChanged()
            })

        invalidateTimer.start()
    }

    override fun onDestroy() {
        invalidateTimer.join()

        super.onDestroy()
    }

    private fun newEmotion() {
        TransitionManager.beginDelayedTransition(root)

        constraintDestination.applyTo(root)
    }

    private fun addEmotion() {
        val kind = et_kind.text.toString()
        val detail = et_detail.text.toString()

        Emotions.getInstance().registerEmotion(kind, detail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                closeEmotion()
            })
    }

    private fun closeEmotion() {
        TransitionManager.beginDelayedTransition(root)

        constraintOrigin.applyTo(root)
    }

}

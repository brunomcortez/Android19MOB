package br.com.psyapp.lib.emotions.ui.emotions_add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.FragmentEmotionsAddBinding
import br.com.psyapp.lib.emotions.persistence.Emotion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmotionsAddFragment : Fragment() {

    private lateinit var binding: FragmentEmotionsAddBinding
    private val args: EmotionsAddFragmentArgs by navArgs()

    private var emotion: Emotion? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_emotions_add, container, false).also {
        binding = FragmentEmotionsAddBinding.bind(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emotion = args.emotion
    }

    private fun saveEmotion() {
        val kind = emotion?.kind ?: ""
        val detail = emotion?.detail ?: ""

        Emotions.I.registerEmotion(kind, detail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            }
            .dispose()
    }

    companion object {

        @JvmStatic
        fun newInstance() = EmotionsAddFragment()
    }
}

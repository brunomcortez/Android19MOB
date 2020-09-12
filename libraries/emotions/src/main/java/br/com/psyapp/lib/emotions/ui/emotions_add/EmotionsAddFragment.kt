package br.com.psyapp.lib.emotions.ui.emotions_add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.FragmentEmotionsAddBinding
import br.com.psyapp.lib.emotions.model.EmotionOption
import br.com.psyapp.lib.emotions.persistence.Emotion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmotionsAddFragment : Fragment() {

    private lateinit var binding: FragmentEmotionsAddBinding
    private val args: EmotionsAddFragmentArgs by navArgs()

    private var emotion: Emotion? = null
    private lateinit var optionsAdapter: EmotionsOptionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_emotions_add, container, false).also {
        binding = FragmentEmotionsAddBinding.bind(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configAdapters()

        emotion = args.emotion
    }

    private fun configAdapters() {
        optionsAdapter = EmotionsOptionsAdapter(
            arrayListOf(
                EmotionOption(R.drawable.ic_very_sad, getString(R.string.emotion_very_sad)),
                EmotionOption(R.drawable.ic_sad, getString(R.string.emotion_sad)),
                EmotionOption(R.drawable.ic_neutral, getString(R.string.emotion_neutral)),
                EmotionOption(R.drawable.ic_happy, getString(R.string.emotion_happy)),
                EmotionOption(R.drawable.ic_very_happy, getString(R.string.emotion_very_happy))
            )
        ) { type, emotion ->
            onSelectEmotion(type, emotion)
        }

        binding.apply {
            rvEmotions.adapter = optionsAdapter
            rvEmotions.layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
        }
    }

    private fun onSelectEmotion(type: EmotionsOptionsAdapter.ActionType, emotion: EmotionOption) {

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

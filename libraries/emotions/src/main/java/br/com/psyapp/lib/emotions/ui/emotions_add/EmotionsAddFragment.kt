package br.com.psyapp.lib.emotions.ui.emotions_add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.FragmentEmotionsAddBinding

class EmotionsAddFragment : Fragment() {

    private lateinit var binding: FragmentEmotionsAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_emotions_add, container, false).also {
        binding = FragmentEmotionsAddBinding.bind(it)
    }

    private fun addEmotion() {
//        val kind = et_kind.text.toString()
//        val detail = et_detail.text.toString()

//        Emotions.getInstance().registerEmotion(kind, detail)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//
//            }
//            .dispose()
    }

    companion object {

        @JvmStatic
        fun newInstance() = EmotionsAddFragment()
    }
}

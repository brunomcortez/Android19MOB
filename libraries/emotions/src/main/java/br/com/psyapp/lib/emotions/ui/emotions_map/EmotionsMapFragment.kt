package br.com.psyapp.lib.emotions.ui.emotions_map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.FragmentEmotionsMapBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class EmotionsMapFragment : Fragment() {

    private val emotionsMapAdapter: EmotionsMapAdapter by inject()

    private lateinit var binding: FragmentEmotionsMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_emotions_map, container, false).also {
        binding = FragmentEmotionsMapBinding.bind(it)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configListeners()
        configLists()
        configObservers()
    }

    private fun configListeners() {
        binding.apply {


            btnNew.setOnClickListener {

            }
        }
    }

    private fun configLists() {
        binding.rvEmotions.apply {
            adapter = emotionsMapAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun configObservers() {
        Emotions.I.listEmotions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                emotionsMapAdapter.registers = it

                emotionsMapAdapter.notifyDataSetChanged()
            }
    }

    private fun newEmotion() {
    }

}

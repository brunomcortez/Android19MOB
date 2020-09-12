package br.com.psyapp.lib.emotions.ui.emotions_map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.FragmentEmotionsMapBinding
import br.com.psyapp.lib.emotions.persistence.Emotion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class EmotionsMapFragment : Fragment(), EmotionsMapListener {

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
        configObservers()
        configAdapter()
    }

    private fun configListeners() {
        binding.apply {
            emotionsMapAdapter.listener = this@EmotionsMapFragment

            btnNew.setOnClickListener {
                newEmotion()
            }
        }
    }

    private fun configObservers() {
        Emotions.I.listEmotions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                emotionsMapAdapter.registers = it

                emotionsMapAdapter.notifyDataSetChanged()

                binding.apply {
                    ivEmpty.visibility = if (it.size > 0) View.GONE else View.VISIBLE
                    tvEmpty.visibility = if (it.size > 0) View.GONE else View.VISIBLE
                }
            }
    }

    private fun configAdapter() {
        binding.rvEmotions.apply {
            adapter = emotionsMapAdapter
            layoutManager = LinearLayoutManager(context)

            ItemTouchHelper(EmotionsMapSwipeActions(emotionsMapAdapter)).also {
                it.attachToRecyclerView(this)
            }
        }
    }

    private fun newEmotion() {
        findNavController().navigate(R.id.addEmotion)
    }

    private fun editEmotion(emotion: Emotion) {
        val action = EmotionsMapFragmentDirections.addEmotion(emotion)

        findNavController().navigate(action)
    }

    override fun onAction(type: Int, position: Int) {
        val emotion = emotionsMapAdapter.registers[position]

        editEmotion(emotion)
    }

}

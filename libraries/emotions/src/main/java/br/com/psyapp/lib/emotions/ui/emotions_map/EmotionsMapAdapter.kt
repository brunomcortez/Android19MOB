package br.com.psyapp.lib.emotions.ui.emotions_map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.ListItemEmotionBinding
import br.com.psyapp.lib.emotions.persistence.Emotion

class EmotionsMapAdapter : RecyclerView.Adapter<EmotionsMapAdapter.ViewHolder>() {

    var registers: List<Emotion> = listOf()
    var listener: EmotionsMapListener? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemEmotionBinding.bind(view)

        fun onBind(position: Int) {
            val emotion = registers[position]

            binding.apply {
                tvKind.text = emotion.kind
                tvRegistered.text = emotion.registered.toString()

                root.setOnClickListener {
                    listener?.onAction(0)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_emotion, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = registers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

}

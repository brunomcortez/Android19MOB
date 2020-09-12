package br.com.psyapp.lib.emotions.ui.emotions_map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.Emotions
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.ListItemEmotionRegisterBinding
import br.com.psyapp.lib.emotions.persistence.Emotion
import java.text.DateFormat

class EmotionsMapAdapter : RecyclerView.Adapter<EmotionsMapAdapter.ViewHolder>() {

    var registers: List<Emotion> = listOf()
    var listener: EmotionsMapListener? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemEmotionRegisterBinding.bind(view)

        fun onBind(position: Int) {
            val emotion = registers[position]

            binding.apply {
                val option = Emotions.I.options.find { it.name == emotion.kind }

                option?.let {
                    ivEmotion.setImageDrawable(
                        AppCompatResources.getDrawable(root.context, it.icon)
                    )
                }

                tvKind.text = root.context.getString(emotion.kind)
                tvRegistered.text = DateFormat.getDateTimeInstance().format(emotion.registered)

                root.setOnClickListener {
                    listener?.onAction(0, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_emotion_register, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = registers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

}

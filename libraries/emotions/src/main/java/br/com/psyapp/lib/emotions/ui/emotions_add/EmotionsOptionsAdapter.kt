package br.com.psyapp.lib.emotions.ui.emotions_add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.databinding.ListItemEmotionOptionBinding
import br.com.psyapp.lib.emotions.model.EmotionOption

class EmotionsOptionsAdapter(
    val options: ArrayList<EmotionOption>,
    private val onAction: (type: ActionType, emotion: EmotionOption) -> Unit
) : RecyclerView.Adapter<EmotionsOptionsAdapter.ViewHolder>() {

    enum class ActionType(type: Int) {
        TOUCH(0), LONG_PRESS(1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemEmotionOptionBinding.bind(view)

        fun onBind(position: Int) {
            val option = options[position]

            binding.apply {
                ivEmotion.setImageDrawable(
                    AppCompatResources.getDrawable(root.context, option.icon)
                )
                tvEmotion.text = root.context.getString(option.name)

                root.setOnClickListener {
                    onAction.invoke(ActionType.TOUCH, option)
                }

                root.alpha = if (option.selected) 1f else .6f
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_emotion_option, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = options.size

}

package br.com.psyapp.lib.emotions.ui.emotions_map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.R
import br.com.psyapp.lib.emotions.persistence.Emotion

class EmotionsMapAdapter : RecyclerView.Adapter<EmotionsMapAdapter.ViewHolder>() {

    var registers: List<Emotion> = listOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val kind = view.findViewById<TextView>(R.id.tv_kind)
        private val registered = view.findViewById<TextView>(R.id.tv_registered)

        fun onBind(position: Int) {
            val emotion = registers[position]

            kind.text = emotion.kind
            registered.text = emotion.registered.toString()
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

package br.com.psyapp.lib.emotions.ui.emotions_add

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EmotionsOptionsAdapter(
    private val options: ArrayList<String>,
    private val onAction: (type: ActionType) -> Unit
) : RecyclerView.Adapter<EmotionsOptionsAdapter.ViewHolder>() {

    enum class ActionType(type: Int) {
        TOUCH(0), LONG_PRESS(1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = options.size

}

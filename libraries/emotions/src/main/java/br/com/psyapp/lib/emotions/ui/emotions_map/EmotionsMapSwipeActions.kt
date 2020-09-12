package br.com.psyapp.lib.emotions.ui.emotions_map

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.psyapp.lib.emotions.Emotions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmotionsMapSwipeActions(
    private val adapter: EmotionsMapAdapter
) : ItemTouchHelper.SimpleCallback(
    0, ItemTouchHelper.LEFT
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> {
                val position = viewHolder.adapterPosition
                val emotion = adapter.registers[position]

                Emotions.I.removeEmotion(emotion)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        adapter.notifyItemRemoved(position)
                    }
            }
        }
    }

}

package `in`.music.extras

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallBack : DiffUtil.ItemCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return true
    }


}
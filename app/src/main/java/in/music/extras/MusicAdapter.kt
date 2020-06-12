@file:Suppress("DEPRECATION")

package `in`.music.extras

import `in`.music.R
import `in`.music.repository.MusicRepository
import `in`.music.viewmodel.MusicViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private var context: FragmentActivity,
    private var callBackListener: CallBackListener
) : PagedListAdapter<Int, MusicAdapter.MusicViewHolder>(DiffUtilCallBack()) {

    private val repository = MusicRepository()
    private val musicFactory = MusicFactory(repository)
    private val musicViewModel = ViewModelProviders.of(context,musicFactory).get(MusicViewModel::class.java)

    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val recyclerView: RecyclerView = view.findViewById(R.id.rcv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.element_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.title.text = ("Nested Horizontal RecyclerView ${position + 1}")

        holder.recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        holder.recyclerView.setHasFixedSize(true)
        val musicAdapter3 = MusicAdapter3(context)
        holder.recyclerView.adapter = musicAdapter3
        holder.recyclerView.setItemViewCacheSize(20)

        musicViewModel.getHorizontalList(context,callBackListener).observe(context, Observer { list ->
            if (list != null){
                musicAdapter3.submitList(list)
                musicAdapter3.notifyDataSetChanged()
            }
        })

    }


}
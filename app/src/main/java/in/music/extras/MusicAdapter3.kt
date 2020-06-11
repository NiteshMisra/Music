package `in`.music.extras

import `in`.music.R
import `in`.music.activity.PlayActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MusicAdapter3(
    private var context: Context
) : PagedListAdapter<Int, MusicAdapter3.MusicViewHolder>(DiffUtilCallBack()) {

    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.name)
        val layout: LinearLayout = view.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.element_music2, parent, false)
        context = parent.context
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = Constants.songName


        Glide.with(context).asBitmap()
            .load(Constants.songImage)
            .apply(RequestOptions().override(100,100))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .into(holder.image)

        holder.layout.setOnClickListener {
            val intent = Intent(context, PlayActivity::class.java)
            context.startActivity(intent)
        }
    }


}
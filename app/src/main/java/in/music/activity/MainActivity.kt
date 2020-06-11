@file:Suppress("DEPRECATION")

package `in`.music.activity

import `in`.music.R
import `in`.music.databinding.ActivityMain2Binding
import `in`.music.extras.*
import `in`.music.repository.MusicRepository
import `in`.music.viewmodel.MusicViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CallBackListener {

    private lateinit var binding : ActivityMain2Binding
    private lateinit var musicAdapter2: MusicAdapter3
    private lateinit var musicViewModel: MusicViewModel
    private lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2)

        val repository = MusicRepository()
        val musicFactory = MusicFactory(repository)
        musicViewModel = ViewModelProviders.of(this,musicFactory).get(MusicViewModel::class.java)

        musicAdapter2 = MusicAdapter3(this)
        binding.hRcv.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.hRcv.setHasFixedSize(true)
        binding.hRcv.adapter = musicAdapter2
        binding.hRcv.setItemViewCacheSize(20)

        musicViewModel.getHorizontalList(this,this).observe(this, Observer {list ->
            if (list != null){
                musicAdapter2.submitList(list)
                musicAdapter2.notifyDataSetChanged()
            }
        })

        musicAdapter = MusicAdapter(this)
        binding.vRcv.layoutManager = GridLayoutManager(this,3)
        binding.vRcv.adapter = musicAdapter
        binding.vRcv.setItemViewCacheSize(20)

        musicViewModel.getMusicList(this,this).observe(this, Observer {list2 ->
            if (list2 != null){
                musicAdapter.submitList(list2)
                musicAdapter.notifyDataSetChanged()
            }
        })

    }

    override fun noInternet() {
        Coroutines.main {
            Toast.makeText(this,"Internet Not Available",Toast.LENGTH_LONG).show()
        }
    }
}
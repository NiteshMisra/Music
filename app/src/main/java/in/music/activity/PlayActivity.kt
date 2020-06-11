@file:Suppress("DEPRECATION")

package `in`.music.activity

import `in`.music.R
import `in`.music.databinding.ActivityPlay2Binding
import `in`.music.extras.Constants
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.r0adkll.slidr.Slidr

class PlayActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlay2Binding
    private lateinit var activity : FragmentActivity
    private var mediaPlayer: MediaPlayer ?= null
    private lateinit var updateSeekbar : Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this@PlayActivity
        binding = DataBindingUtil.setContentView(activity,R.layout.activity_play2)

        Slidr.attach(activity)

        Glide.with(activity).load(Constants.songImage).into(binding.image)

        if (focusChangeListener != null) {
            val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            am.requestAudioFocus(
                focusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            )
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer!!.seekTo(seekBar!!.progress)
            }

        })

        initMediaPlayer()

        binding.play.setOnClickListener {
            if (mediaPlayer != null){
                mediaPlayer!!.start()
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE
            }
        }

        binding.pause.setOnClickListener {
            if (mediaPlayer != null){
                mediaPlayer!!.pause()
                binding.play.visibility = View.VISIBLE
                binding.pause.visibility = View.GONE
            }
        }

    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(activity, R.raw.cyanide_sisters)
        mediaPlayer!!.start()
        binding.seekBar.max = mediaPlayer!!.duration
        updateSeekBar()
        binding.pause.visibility = View.VISIBLE
        binding.play.visibility = View.GONE
    }

    private fun updateSeekBar() {
        updateSeekbar = object : Thread(){
            override fun run() {
                super.run()

                val totalDuration = mediaPlayer!!.duration
                val currentPosition = mediaPlayer!!.currentPosition

                while (currentPosition < totalDuration){
                    try{

                        sleep(1000)
                        binding.seekBar.progress = mediaPlayer!!.currentPosition

                    }catch (e : IllegalStateException){
                        e.printStackTrace()
                    }
                }

            }
        }
        updateSeekbar.start()
    }

    private val focusChangeListener = try {
        AudioManager.OnAudioFocusChangeListener { }
    } catch (e: Exception) {
        null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null){
            if (mediaPlayer!!.isPlaying){
                mediaPlayer!!.stop()
            }
            mediaPlayer!!.release()
        }
    }
}

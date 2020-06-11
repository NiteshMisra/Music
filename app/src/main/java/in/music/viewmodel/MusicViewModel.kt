package `in`.music.viewmodel

import `in`.music.extras.CallBackListener
import `in`.music.repository.MusicRepository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class MusicViewModel(
    private var musicRepository: MusicRepository
) : ViewModel() {

    fun getMusicList(context: Context, callBackListener: CallBackListener) : LiveData<PagedList<Int>>{
        return musicRepository.getMusicList(context,callBackListener)
    }

    fun getHorizontalList(context: Context,callBackListener: CallBackListener): LiveData<PagedList<Int>>{
        return musicRepository.getHMusicList(context,callBackListener)
    }

}
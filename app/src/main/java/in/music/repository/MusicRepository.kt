package `in`.music.repository

import `in`.music.datasource.MusicDataSourceFactory
import `in`.music.extras.CallBackListener
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class MusicRepository {

    fun getMusicList(context: Context,callBackListener: CallBackListener) : LiveData<PagedList<Int>> {

        val musicDataSourceFactory = MusicDataSourceFactory(context, callBackListener)
        val musicDataSource = musicDataSourceFactory.getLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(musicDataSourceFactory, config).build()
    }

    fun getHMusicList(context: Context,callBackListener: CallBackListener) : LiveData<PagedList<Int>> {

        val musicDataSourceFactory = MusicDataSourceFactory(context, callBackListener)
        val musicDataSource = musicDataSourceFactory.getLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(musicDataSourceFactory, config).build()
    }

}
package `in`.music.datasource

import `in`.music.extras.CallBackListener
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class MusicDataSourceFactory(
    private var context: Context,
    private var callBackListener: CallBackListener
) : DataSource.Factory<Int,Int>() {

    private var musicLiveDataSource : MutableLiveData<PageKeyedDataSource<Int,Int>> = MutableLiveData()

    override fun create(): DataSource<Int, Int> {
        val musicDataSource = MusicDataSource(context,callBackListener)
        musicLiveDataSource.postValue(musicDataSource)
        return musicDataSource
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int,Int>> {
        return musicLiveDataSource
    }
}
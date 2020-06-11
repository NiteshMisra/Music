package `in`.music.datasource

import `in`.music.extras.AppUtils
import `in`.music.extras.CallBackListener
import `in`.music.extras.Constants
import android.content.Context
import androidx.paging.PageKeyedDataSource

class MusicDataSource(
    private var context: Context,
    private var callBackListener: CallBackListener
) : PageKeyedDataSource<Int, Int>() {

    private val dataSize = 20

    private var pageNumber: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Int>
    ) {
        if (AppUtils.isInternetAvailable(context)) {
            val songsList: ArrayList<Int> = ArrayList()
            for (i in 0..dataSize) {
                songsList.add(Constants.mySong)
            }
            pageNumber += 1
            callback.onResult(songsList, null, pageNumber)
        } else {
            callBackListener.noInternet()
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Int>) {
        if (AppUtils.isInternetAvailable(context)) {
            val songsList: ArrayList<Int> = ArrayList()
            for (i in 0..dataSize) {
                songsList.add(Constants.mySong)
            }
            pageNumber += 1
            callback.onResult(songsList, pageNumber)
        } else {
            callBackListener.noInternet()
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Int>) {

    }
}
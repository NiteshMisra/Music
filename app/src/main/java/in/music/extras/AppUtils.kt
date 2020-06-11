@file:Suppress("DEPRECATION")

package `in`.music.extras

import android.content.Context
import android.net.ConnectivityManager

class AppUtils {

    companion object {

        fun isInternetAvailable(context: Context) : Boolean{
            val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

    }

}
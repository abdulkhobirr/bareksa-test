package khobir.abdul.bareksa_test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Utils {
    fun isNetworkAvailable(context: Context): Boolean {
        return try {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo: NetworkInfo? = null
            if (manager != null) {
                networkInfo = manager.activeNetworkInfo
            }
            networkInfo != null && networkInfo.isConnected
        } catch (e: NullPointerException) {
            false
        }
    }
}
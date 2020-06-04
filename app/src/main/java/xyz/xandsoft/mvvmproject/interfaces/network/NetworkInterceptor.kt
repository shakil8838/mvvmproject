package xyz.xandsoft.mvvmproject.interfaces.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import xyz.xandsoft.mvvmproject.utills.NetworkException

class NetworkInterceptor(context: Context) : Interceptor {

    private val context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isNetworkAvailable())
            throw NetworkException("Make sure you have active internet connection")

        return chain.proceed(chain.request())
    }

    private fun isNetworkAvailable(): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}
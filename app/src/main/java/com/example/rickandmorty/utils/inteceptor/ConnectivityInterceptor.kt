package com.example.rickandmorty.utils.inteceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.rickandmorty.R
import com.example.rickandmorty.utils.inteceptor.error.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(
    private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw NetworkException.ConnectivityException(context.getString(R.string.error_connectivity))
        }

        val httpUrlBuilder = chain
            .request()
            .url
            .newBuilder()

        val httpUrl = httpUrlBuilder.build()
        return chain.proceed(chain.request().newBuilder().url(httpUrl).build())
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        connectivityManager.apply {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            } else {
                connectivityManager.activeNetworkInfo?.isConnected
                    ?: false
            }
        }
    }
}
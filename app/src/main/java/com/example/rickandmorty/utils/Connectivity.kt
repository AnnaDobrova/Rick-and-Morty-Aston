package com.example.rickandmorty.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class Connectivity @Inject constructor(
    private val context: Context
) {
    fun isNetworkAvailable(): Boolean {
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
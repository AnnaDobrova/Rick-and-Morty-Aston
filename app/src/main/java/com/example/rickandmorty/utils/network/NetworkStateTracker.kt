package com.example.rickandmorty.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStateTracker(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow {
        val networkStateCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onUnavailable() {
                trySend(NetworkState.Unavailable).isSuccess
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkState.Available).isSuccess
            }

            override fun onLost(network: Network) {
                trySend(NetworkState.Unavailable).isSuccess
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStateCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStateCallback)
        }
    }
}
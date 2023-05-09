package com.example.rickandmorty.utils.network

sealed class NetworkState {
    object Available : NetworkState()
    object Unavailable : NetworkState()
}
package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.locations.api.LocationNetworkDataSours
import com.example.rickandmorty.data.locations.model.LocationsData
import com.example.rickandmorty.presentation.locations.LocationsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationsRepository {

    private var locationsNetworkDataSource: LocationNetworkDataSours? = null
    private var listener: LocationsListener? = null

    init {
        locationsNetworkDataSource = RetrofitClient.fillRetrofit().create(LocationNetworkDataSours::class.java)
    }

    fun registerListener(listener: LocationsListener) {
        this.listener = listener
    }

    fun loadCharacters() {
        locationsNetworkDataSource?.getAllLocation()?.enqueue(object : Callback<LocationsData> {
            override fun onResponse(call: Call<LocationsData>, response: Response<LocationsData>) {
                listener?.getAllLocations(response.body()?.locations ?: emptyList())
            }

            override fun onFailure(call: Call<LocationsData>, t: Throwable) {
                listener?.getAllLocations(emptyList())
            }
        })
    }
}
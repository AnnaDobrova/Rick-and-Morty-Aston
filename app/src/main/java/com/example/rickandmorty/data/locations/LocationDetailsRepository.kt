package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.locations.api.LocationDetailsNetworkDataSours
import com.example.rickandmorty.data.locations.model.LocationsData
import com.example.rickandmorty.presentation.locations.LocationDetailsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationDetailsRepository {

    private var locationDetailsNetworkDataSource: LocationDetailsNetworkDataSours? = null
    private var listener: LocationDetailsListener? = null

    init {
        locationDetailsNetworkDataSource =
            RetrofitClient.fillRetrofit().create(LocationDetailsNetworkDataSours::class.java)
    }

    fun registerListener(listener: LocationDetailsListener) {
        this.listener = listener
    }

    fun loadLocationById(id: Int) {
        locationDetailsNetworkDataSource?.getLocationDetails(id)
            ?.enqueue(object : Callback<LocationsData.SingleLocationData>{
                override fun onResponse(
                    call: Call<LocationsData.SingleLocationData>,
                    response: Response<LocationsData.SingleLocationData>
                ) {
                    response.body()?.let { listener?.getLocationById(it) }
                }

                override fun onFailure(call: Call<LocationsData.SingleLocationData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
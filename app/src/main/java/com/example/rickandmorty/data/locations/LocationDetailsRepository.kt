package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.locations.detail.api.LocationDetailsNetworkDataSours
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
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
            ?.enqueue(object : Callback<LocationDetailData>{
                override fun onResponse(
                    call: Call<LocationDetailData>,
                    response: Response<LocationDetailData>
                ) {
                    response.body()?.let { listener?.getLocationById(it) }
                }

                override fun onFailure(call: Call<LocationDetailData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
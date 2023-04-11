package com.example.rickandmorty.data.locations.api

import com.example.rickandmorty.data.locations.model.LocationsData
import retrofit2.Call
import retrofit2.http.GET

interface LocationNetworkDataSours {

    @GET("location")
    fun getAllLocation(): Call<LocationsData>
}
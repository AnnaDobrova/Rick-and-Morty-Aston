package com.example.rickandmorty.data.locations.api

import com.example.rickandmorty.data.locations.model.LocationsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationDetailsNetworkDataSours {

    @GET("location/{id}")
    fun getLocationDetails (@Path("id") locationId: Int) : Call<LocationsData.SingleLocationData>
}
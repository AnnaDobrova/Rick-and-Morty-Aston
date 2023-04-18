package com.example.rickandmorty.data.locations.detail.api

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationDetailsNetworkDataSours {

    @GET("location/{id}")
    fun getLocationDetails (@Path("id") locationId: Int) : Call<LocationDetailData>
}
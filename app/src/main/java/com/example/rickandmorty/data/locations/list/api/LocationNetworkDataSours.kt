package com.example.rickandmorty.data.locations.list.api

import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.data.locations.list.model.SingleLocationData
import retrofit2.Response
import retrofit2.http.GET

interface LocationNetworkDataSours {

    @GET("location")
   suspend fun getAllLocation(): Response<LocationsData>
}
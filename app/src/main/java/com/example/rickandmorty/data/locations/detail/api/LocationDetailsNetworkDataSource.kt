package com.example.rickandmorty.data.locations.detail.api

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationDetailsNetworkDataSource {

    @GET("location/{id}")
   suspend fun getLocationDetails (@Path("id") locationId: Int) : Response<LocationDetailData>
}
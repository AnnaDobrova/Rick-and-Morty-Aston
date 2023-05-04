package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.locations.list.api.LocationNetworkDataSours
import com.example.rickandmorty.data.locations.list.mapper.LocationListDataToLocationListDomainMapper
import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.domain.location.list.LocationListFromDataToDomainCallback
import com.example.rickandmorty.domain.location.list.LocationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private var locationsNetworkDataSource: LocationNetworkDataSours,
    private val mapperFromDataToDomain: LocationListDataToLocationListDomainMapper
) : LocationRepository {

    private var callbackFromDataToDomain: LocationListFromDataToDomainCallback? = null

    override fun registerFromDataToDomainCallback(callback: LocationListFromDataToDomainCallback) {
        callbackFromDataToDomain = callback
    }

    override fun loadLocation() {
        locationsNetworkDataSource.getAllLocation().enqueue(object : Callback<LocationsData> {
            override fun onResponse(call: Call<LocationsData>, response: Response<LocationsData>) {
                callbackFromDataToDomain?.getAllLocationsFromDataToDomain(
                    mapperFromDataToDomain.map(
                        response.body()?.locations ?: emptyList()
                    )
                )
            }

            override fun onFailure(call: Call<LocationsData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
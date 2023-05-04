package com.example.rickandmorty.data.locations.detail

import com.example.rickandmorty.data.locations.detail.api.LocationDetailsNetworkDataSource
import com.example.rickandmorty.data.locations.detail.mapper.LocationDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.domain.location.detail.LocationDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.location.detail.LocationDetailRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationDetailsRepositoryImpl @Inject constructor(
    private var locationDetailsNetworkDataSource: LocationDetailsNetworkDataSource,
    private val mapperFromDataToDomain:LocationDetailDataToEpisodeDetailDomainMapper
): LocationDetailRepository {

    private var callbackFromDataToDomainCallback: LocationDetailFromDataToDomainCallback? = null

    override fun registerFromDataToDomainCallback(callback: LocationDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadLocations(id: Int) {
        locationDetailsNetworkDataSource.getLocationDetails(id)
            .enqueue(object : Callback<LocationDetailData> {
                override fun onResponse(call: Call<LocationDetailData>, response: Response<LocationDetailData>) {
                    response.body()?.let {
                        callbackFromDataToDomainCallback?.getLocationById(
                            mapperFromDataToDomain.map(it)
                        )
                    }
                }

                override fun onFailure(call: Call<LocationDetailData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
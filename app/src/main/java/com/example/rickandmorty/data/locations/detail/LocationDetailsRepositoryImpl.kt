package com.example.rickandmorty.data.locations.detail

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.locations.detail.api.LocationDetailsNetworkDataSource
import com.example.rickandmorty.data.locations.detail.mapper.LocationDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.domain.location.detail.LocationDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.location.detail.LocationDetailRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationDetailsRepositoryImpl : LocationDetailRepository {

    private var locationDetailsNetworkDataSource: LocationDetailsNetworkDataSource? = null

    private var callbackFromDataToDomainCallback: LocationDetailFromDataToDomainCallback? = null

    private val mapperFromDataToDomain by lazy {
        LocationDetailDataToEpisodeDetailDomainMapper()
    }

    init {
        locationDetailsNetworkDataSource =
            RetrofitClient.fillRetrofit().create(LocationDetailsNetworkDataSource::class.java)
    }

    override fun registerFromDataToDomainCallback(callback: LocationDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadLocations(id: Int) {
        locationDetailsNetworkDataSource?.getLocationDetails(id)
            ?.enqueue(object : Callback<LocationDetailData> {
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
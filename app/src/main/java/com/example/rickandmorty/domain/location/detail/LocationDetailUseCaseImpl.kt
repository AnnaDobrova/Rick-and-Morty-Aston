package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.data.locations.detail.LocationDetailsRepositoryImpl
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.LocationDetailFromDomainToUiCallback

class LocationDetailUseCaseImpl : LocationDetailUseCase, LocationDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: LocationDetailFromDomainToUiCallback? = null
    private val locationDetailRepository: LocationDetailsRepositoryImpl by lazy {
        LocationDetailsRepositoryImpl()
    }

    init {
        locationDetailRepository.registerFromDataToDomainCallback(this)
    }

    override fun getLocationById(locationDetail: LocationDetailsDomain) {
        callbackFromDomainToUiCallback?.getLocationDetail(locationDetail)
    }

    override fun registerFromDomainToUiCallback(callback: LocationDetailFromDomainToUiCallback) {
        callbackFromDomainToUiCallback = callback
    }

    override fun loadLocationById(id: Int) {
        locationDetailRepository.loadLocations(id)
    }

}
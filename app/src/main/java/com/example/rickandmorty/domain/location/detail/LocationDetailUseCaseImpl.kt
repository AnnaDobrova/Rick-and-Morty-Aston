package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.LocationDetailFromDomainToUiCallback
import javax.inject.Inject

class LocationDetailUseCaseImpl @Inject constructor(
    private val locationDetailRepository: LocationDetailRepository
) : LocationDetailUseCase, LocationDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: LocationDetailFromDomainToUiCallback? = null
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
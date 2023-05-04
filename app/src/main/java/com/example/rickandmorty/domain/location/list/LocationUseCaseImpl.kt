package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.LocationListFromDomainToUiCallback
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationUseCase, LocationListFromDataToDomainCallback {

    private var callbackFromDomainToUi: LocationListFromDomainToUiCallback? = null

    init {
        locationRepository.registerFromDataToDomainCallback(this)
    }

    override fun getAllLocationsFromDataToDomain(locationList: List<SingleLocationDomain>) {
        callbackFromDomainToUi?.getAllLocationsFromDomainToUi(locationList)
    }

    override fun registerFromDomainToUiCallback(callback: LocationListFromDomainToUiCallback) {
        callbackFromDomainToUi = callback
    }

    override fun loadLocations() {
        locationRepository.loadLocation()
    }

}
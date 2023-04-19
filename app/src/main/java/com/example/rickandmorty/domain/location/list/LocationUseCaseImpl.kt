package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.data.locations.LocationsRepositoryImpl
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.LocationListFromDomainToUiCallback

class LocationUseCaseImpl : LocationUseCase, LocationListFromDataToDomainCallback {

    private val locationRepository: LocationRepository by lazy {
        LocationsRepositoryImpl()
    }

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
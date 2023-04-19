package com.example.rickandmorty.presentation.locations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.domain.location.list.LocationUseCaseImpl
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

class LocationsViewModel : ViewModel(), LocationListFromDomainToUiCallback {

    private var locations = MutableLiveData<List<SingleLocationUI>>(emptyList())
    private var locationsUseCase: LocationUseCase? = null
    private val mapperFromDomainToUi by lazy {
        SingleLocationDomainToSingleLocationUiMapper()
    }

    init {
        locationsUseCase = LocationUseCaseImpl()
        locationsUseCase?.registerFromDomainToUiCallback(this)
        loadAllLocations()
    }

    override fun getAllLocationsFromDomainToUi(locationsList: List<SingleLocationDomain>) {
        locations.postValue(
            mapperFromDomainToUi.map(locationsList)
        )
    }

    fun getLocations(): LiveData<List<SingleLocationUI>> = locations

    private fun loadAllLocations() {
        locationsUseCase?.loadLocations()
    }
}
package com.example.rickandmorty.presentation.locations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationUseCase,
    private val mapperFromDomainToUi:   SingleLocationDomainToSingleLocationUiMapper
) : ViewModel(), LocationListFromDomainToUiCallback {

    private var locations = MutableLiveData<List<SingleLocationUI>>(emptyList())


    init {
        locationsUseCase.registerFromDomainToUiCallback(this@LocationsViewModel)
        loadAllLocations()
    }

    override fun getAllLocationsFromDomainToUi(locationsList: List<SingleLocationDomain>) {
        locations.postValue(
            mapperFromDomainToUi.map(locationsList)
        )
    }

    fun getLocations(): LiveData<List<SingleLocationUI>> = locations

    fun loadAllLocations() {
        locationsUseCase.loadLocations()
    }
}
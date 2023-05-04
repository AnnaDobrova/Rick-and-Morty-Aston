package com.example.rickandmorty.presentation.locations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private var locationDetailUseCase: LocationDetailUseCase,
    private val mapperFromDomainToUi: LocationDetailsDomainToLocationDetailsUIMapper
) : ViewModel(), LocationDetailFromDomainToUiCallback {

    private var locations = MutableLiveData<LocationDetailUi>()


    init {
        locationDetailUseCase.registerFromDomainToUiCallback(this@LocationDetailViewModel)
    }

    override fun getLocationDetail(locationDetail: LocationDetailsDomain) {
        locations.postValue(mapperFromDomainToUi.map(locationDetail))
    }

    fun loadLocationById(id: Int) {
        locationDetailUseCase.loadLocationById(id)
    }

    fun getLocation(): LiveData<LocationDetailUi> = locations
}
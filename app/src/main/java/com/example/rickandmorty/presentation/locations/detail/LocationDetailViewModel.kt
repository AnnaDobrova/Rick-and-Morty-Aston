package com.example.rickandmorty.presentation.locations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCaseImpl
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi

class LocationDetailViewModel : ViewModel(), LocationDetailFromDomainToUiCallback {

    private var locations = MutableLiveData<LocationDetailUi>()
    private var locationDetailUseCase: LocationDetailUseCase? = null
    private val mapperFromDomainToUi by lazy {
        LocationDetailsDomainToLocationDetailsUIMapper()
    }

    init {
        locationDetailUseCase = LocationDetailUseCaseImpl()
        locationDetailUseCase?.registerFromDomainToUiCallback(this@LocationDetailViewModel)
    }

    override fun getLocationDetail(locationDetail: LocationDetailsDomain) {
        locations.postValue(mapperFromDomainToUi.map(locationDetail))
    }

    fun loadLocationById(id: Int) {
        locationDetailUseCase?.loadLocationById(id)
    }

    fun getLocation(): LiveData<LocationDetailUi> = locations
}
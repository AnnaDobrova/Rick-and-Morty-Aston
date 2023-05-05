package com.example.rickandmorty.presentation.locations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private var locationDetailUseCase: LocationDetailUseCase,
    private val mapperFromDomainToUi: LocationDetailsDomainToLocationDetailsUIMapper
) : ViewModel() {

    private var locations = MutableLiveData<LocationDetailUi>()

    fun loadLocationById(id: Int) {
        viewModelScope.launch {
            locationDetailUseCase.loadLocationById(id).collect() { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        locations.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        Throwable(annaResponse.error)
                    }
                }
            }
        }
    }

    fun getLocation(): LiveData<LocationDetailUi> = locations
}
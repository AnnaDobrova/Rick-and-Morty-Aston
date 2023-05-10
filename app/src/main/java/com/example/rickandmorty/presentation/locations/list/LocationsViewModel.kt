package com.example.rickandmorty.presentation.locations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationUseCase,
    private val mapperFromDomainToUi: SingleLocationDomainToSingleLocationUiMapper
) : ViewModel() {

    private var locations = MutableLiveData<List<SingleLocationUI>>(emptyList())

    private val error = MutableLiveData<String>()

    init {
        loadAllLocations()
    }

    fun loadAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = locationsUseCase.getAllLocations()) {
                is AnnaResponse.Success -> locations.postValue(mapperFromDomainToUi.map(response.data))
                is AnnaResponse.Failure -> {
                    error.postValue(response.error.message)
                    loadAllEpisodeFromLocal()
                }
            }
        }
    }

    private fun loadAllEpisodeFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = locationsUseCase.getAllLocationFromLocal()) {
                is AnnaResponse.Success -> locations.postValue(mapperFromDomainToUi.map(response.data))
                is AnnaResponse.Failure -> {
                    error.postValue(response.error.message)
                }
            }
        }
    }

    fun getLocations(): LiveData<List<SingleLocationUI>> = locations
    fun getError(): LiveData<String> = error

}
package com.example.rickandmorty.presentation.locations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationUseCase,
    private val mapperFromDomainToUi:   SingleLocationDomainToSingleLocationUiMapper
) : ViewModel(){

    private var locations = MutableLiveData<List<SingleLocationUI>>(emptyList())

    init {
        loadAllLocations()
    }

    fun loadAllLocations() {
        viewModelScope.launch (Dispatchers.IO){
           locations.postValue(mapperFromDomainToUi.map(locationsUseCase.getAllLocations()))
        }
    }
    fun getLocations(): LiveData<List<SingleLocationUI>> = locations


}
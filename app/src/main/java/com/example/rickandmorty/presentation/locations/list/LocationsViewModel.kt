package com.example.rickandmorty.presentation.locations.list

import android.app.assist.AssistStructure.ViewNode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.location.list.LocationUseCase
import com.example.rickandmorty.presentation.locations.list.mapper.SingleLocationDomainToSingleLocationUiMapper
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.Connectivity
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationUseCase,
    private val mapperFromDomainToUi: SingleLocationDomainToSingleLocationUiMapper,
    private val connectivity: Connectivity
) : ViewModel() {

    private var locations = MutableStateFlow<ViewState<List<SingleLocationUI>>>(ViewState.Loading)

    init {
        loadLocations()
    }

    private fun loadAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationsUseCase.getAllLocations().collect { annaResponse ->
                locations.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure -> ViewState.Error(annaResponse.error)
                }
            }
        }
    }

    private fun loadAllEpisodeFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            locationsUseCase.getAllLocationFromLocal().collect { annaResponse ->
                locations.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure -> ViewState.Error(annaResponse.error)
                }
            }
        }
    }

    fun loadLocations() {
        if (connectivity.isNetworkAvailable()) {
            loadAllLocations()
        } else {
            loadAllEpisodeFromLocal()
            locations.value = ViewState.Error(Throwable("Отсутствует интернет соединение"))
        }
    }

    fun getLocations(): MutableStateFlow<ViewState<List<SingleLocationUI>>> = locations
}
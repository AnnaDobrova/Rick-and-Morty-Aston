package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.presentation.locations.list.LocationListFromDomainToUiCallback
import retrofit2.Callback

interface LocationUseCase {
    fun registerFromDomainToUiCallback(callback: LocationListFromDomainToUiCallback)
    fun loadLocations()
}
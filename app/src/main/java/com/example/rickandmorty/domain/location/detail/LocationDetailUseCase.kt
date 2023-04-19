package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.presentation.locations.detail.LocationDetailFromDomainToUiCallback

interface LocationDetailUseCase {
    fun registerFromDomainToUiCallback(callback: LocationDetailFromDomainToUiCallback)
    fun loadLocationById(id: Int)
}
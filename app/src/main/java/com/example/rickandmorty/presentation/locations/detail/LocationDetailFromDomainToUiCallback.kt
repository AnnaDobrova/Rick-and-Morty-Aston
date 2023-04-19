package com.example.rickandmorty.presentation.locations.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain

interface LocationDetailFromDomainToUiCallback {
    fun getLocationDetail(locationDetail: LocationDetailsDomain)
}
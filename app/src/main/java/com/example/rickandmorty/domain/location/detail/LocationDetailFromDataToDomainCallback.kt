package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain

interface LocationDetailFromDataToDomainCallback {
    fun getLocationById(locationDetail: LocationDetailsDomain)
}
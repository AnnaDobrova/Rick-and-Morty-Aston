package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain

interface LocationListFromDataToDomainCallback {
    fun getAllLocationsFromDataToDomain(locationList: List<SingleLocationDomain>)
}
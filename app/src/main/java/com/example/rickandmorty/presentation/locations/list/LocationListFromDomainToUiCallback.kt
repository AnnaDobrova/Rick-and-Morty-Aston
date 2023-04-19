package com.example.rickandmorty.presentation.locations.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain

interface LocationListFromDomainToUiCallback {

    fun getAllLocationsFromDomainToUi(locationsList: List<SingleLocationDomain>)
}
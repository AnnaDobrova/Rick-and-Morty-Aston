package com.example.rickandmorty.presentation.locations

import com.example.rickandmorty.data.locations.model.LocationsData

interface LocationsListener {

    fun getAllLocations(locations: List<LocationsData.SingleLocationData>)
}
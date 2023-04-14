package com.example.rickandmorty.presentation.locations

import com.example.rickandmorty.data.locations.model.LocationsData

interface LocationDetailsListener {

    fun getLocationById(location: LocationsData.SingleLocationData)
}
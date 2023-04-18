package com.example.rickandmorty.presentation.locations

import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.data.locations.list.model.SingleLocationData

interface LocationsListener {

    fun getAllLocations(locations: List<SingleLocationData>)
}
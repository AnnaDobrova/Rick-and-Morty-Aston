package com.example.rickandmorty.presentation.locations.mapper

import com.example.rickandmorty.data.locations.model.LocationsData
import com.example.rickandmorty.domain.location.Location

class LocationDataToLocationMap {

    fun map(from: List<LocationsData.SingleLocationData>): List<Location> {
        return from.map {
            Location(
                id = it.id.toInt(),
                nameLocation = it.name,
                typeLocation = it.type,
                dimensionLocation = it.dimension
            )
        }
    }
}
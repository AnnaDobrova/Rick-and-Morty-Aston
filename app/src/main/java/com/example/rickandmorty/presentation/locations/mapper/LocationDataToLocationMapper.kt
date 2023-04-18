package com.example.rickandmorty.presentation.locations.mapper

import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.data.locations.list.model.SingleLocationData
import com.example.rickandmorty.domain.location.list.Location

class LocationDataToLocationMapper {

    fun map(from: List<SingleLocationData>): List<Location> {
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
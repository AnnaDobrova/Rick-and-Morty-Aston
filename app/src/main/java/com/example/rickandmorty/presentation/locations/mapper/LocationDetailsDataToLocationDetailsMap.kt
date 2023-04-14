package com.example.rickandmorty.presentation.locations.mapper

import com.example.rickandmorty.data.locations.model.LocationsData
import com.example.rickandmorty.domain.location.detail.LocationDetails

class LocationDetailsDataToLocationDetailsMap {

    fun map(from: LocationsData.SingleLocationData) = LocationDetails(
        id = from.id.toInt(),
        nameLocation = from.name,
        typeLocation = from.type,
        dimensionLocation = from.dimension,
        residents = from.listCharactersInLocation,
        url = from.url,
        created = from.created
    )

}
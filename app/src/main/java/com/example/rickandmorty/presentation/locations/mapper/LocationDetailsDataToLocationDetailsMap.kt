package com.example.rickandmorty.presentation.locations.mapper

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.domain.location.detail.LocationDetails

class LocationDetailsDataToLocationDetailsMap {

    fun map(from: LocationDetailData) = LocationDetails(
        id = from.id.toInt(),
        nameLocation = from.name,
        typeLocation = from.type,
        dimensionLocation = from.dimension,
        residents = from.listCharactersInLocation,
        url = from.url,
        created = from.created
    )
}


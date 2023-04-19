package com.example.rickandmorty.presentation.locations.detail.mapper

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi

class LocationDetailsDomainToLocationDetailsUIMapper {

    fun map(from: LocationDetailsDomain) = LocationDetailUi(
        id = from.id.toLong(),
        name = from.nameLocation,
        type = from.typeLocation,
        dimension = from.dimensionLocation,
        listCharactersInLocation = from.residents,
        url = from.url,
        created = from.created
    )
}


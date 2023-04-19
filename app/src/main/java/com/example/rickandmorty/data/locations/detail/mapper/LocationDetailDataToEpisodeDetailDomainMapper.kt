package com.example.rickandmorty.data.locations.detail.mapper

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain

class LocationDetailDataToEpisodeDetailDomainMapper {
    fun map(from: LocationDetailData) = LocationDetailsDomain(
        id = from.id,
        nameLocation = from.name,
        typeLocation = from.type,
        dimensionLocation = from.dimension,
        residents = from.listCharactersInLocation,
        url = from.url,
        created = from.created
    )
}
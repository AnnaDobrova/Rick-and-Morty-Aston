package com.example.rickandmorty.data.locations.list.mapper

import com.example.rickandmorty.data.locations.list.model.SingleLocationData
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain

class LocationListDataToLocationListDomainMapper {
    fun map(from: List<SingleLocationData>) = from.map {
        SingleLocationDomain(
            id = it.id.toInt(),
            nameLocation = it.name,
            typeLocation = it.type,
            dimensionLocation = it.dimension,
            listCharactersInLocation = it.listCharactersInLocation,
            url = it.url,
            created = it.created
        )
    }
}
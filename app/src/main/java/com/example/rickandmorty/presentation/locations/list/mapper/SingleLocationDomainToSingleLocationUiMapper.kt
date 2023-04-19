package com.example.rickandmorty.presentation.locations.list.mapper

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

class SingleLocationDomainToSingleLocationUiMapper {

    fun map(from: List<SingleLocationDomain>): List<SingleLocationUI> {
        return from.map {
            SingleLocationUI(
                id = it.id.toLong(),
                name = it.nameLocation,
                type = it.typeLocation,
                dimension = it.dimensionLocation,
                listCharactersInLocation = it.listCharactersInLocation,
                url = it.url,
                created = it.created
            )
        }
    }
}
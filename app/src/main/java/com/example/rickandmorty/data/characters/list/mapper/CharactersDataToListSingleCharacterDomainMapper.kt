package com.example.rickandmorty.data.characters.list.mapper

import com.example.rickandmorty.data.characters.list.model.CharactersData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.locations.model.LocationsData
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.domain.character.list.model.SingleCharacterLocationDomain
import com.example.rickandmorty.domain.character.list.model.SingleCharacterOriginDomain

class CharactersDataToListSingleCharacterDomainMapper {

    fun map(from: List<SingleCharacterData>) = from.map {
        SingleCharacterDomain(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            type = it.type,
            gender = it.gender,
            origin = mapOrigin(it.origin),
            location = mapLocation(it.location),
            image = it.image,
            episodes = it.episodes,
            url = it.url,
            created = it.created,
        )
    }

    private fun mapOrigin(from: SingleCharacterOriginData) = SingleCharacterOriginDomain(
        url = from.url,
        name = from.name
    )

    private fun mapLocation(from: SingleCharacterLocationData) = SingleCharacterLocationDomain(
        url = from.url,
        name = from.name
    )
}
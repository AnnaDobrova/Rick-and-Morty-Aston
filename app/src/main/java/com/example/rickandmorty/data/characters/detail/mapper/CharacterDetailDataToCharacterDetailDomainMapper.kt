package com.example.rickandmorty.data.characters.detail.mapper

import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailLocationData
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailOriginData
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailLocationDomain
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailOriginDomain

class CharacterDetailDataToCharacterDetailDomainMapper {

    fun map(from: CharacterDetailData) =
        CharacterDetailDomain(
            id = from.characterDetailId,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            origin = mapOrigin(from.origin),
            location = mapLocation(from.location),
            image = from.image,
            episodes = from.episodes,
            url = from.url,
            created = from.created,
        )

    private fun mapOrigin(from: CharacterDetailOriginData) = CharacterDetailOriginDomain(
        url = from.url,
        name = from.name
    )

    private fun mapLocation(from: CharacterDetailLocationData) = CharacterDetailLocationDomain(
        url = from.url,
        name = from.name
    )
}
package com.example.rickandmorty.presentation.characters.detail.mapper

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailLocationDomain
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailLocationUi
import com.example.characters.impl.presentation.detail.model.CharacterDetailUi

class CharacterDetailDomainToCharacterDetailUiMapper {

    fun map(from: CharacterDetailDomain) = CharacterDetailUi(
        id = from.id.toInt(),
        image = from.image,
        name = from.name,
        species = from.species,
        gender = from.gender,
        status = from.status,
        episodeList = from.episodes,
        location = mapLocation(from.location)
    )

    private fun mapLocation(from: CharacterDetailLocationDomain) =
        CharacterDetailLocationUi(
            name = from.name,
            url = from.url
        )
}
package com.example.rickandmorty.presentation.characters.mapper

import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.domain.character.detail.CharacterDetail
import com.example.rickandmorty.domain.character.list.Character

class CharacterDetailsDataToCharacterMapper {

    fun map(from: CharactersData.CharacterDetailsData) = CharacterDetail(
        id = from.id.toInt(),
        image = from.image,
        name = from.name,
        species = from.species,
        gender = from.gender,
        status = from.status,
        episodeList = from.episodes,
        location = mapLocation(from.location)
    )

    private fun mapLocation(from: CharactersData.CharacterDetailsData.LocationData) =
        CharacterDetail.LocationDetail(
            name = from.name,
            url = from.url
        )
}
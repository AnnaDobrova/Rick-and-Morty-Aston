package com.example.rickandmorty.presentation.characters.mapper

import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.domain.character.Character

class CharacterDetailsDataToCharacterMapper {

    fun map(from: CharactersData.CharacterDetailsData)= Character(
        id = from.id.toInt(),
        image = from.image,
        name = from.name,
        species = from.species,
        gender = from.gender,
        status = from.status,
    )
}
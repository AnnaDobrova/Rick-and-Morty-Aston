package com.example.rickandmorty.presentation.characters.mapper

import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.domain.character.Character

/**
 * Преобразует данные из 1 вида датакласса в другой, необходимый нам
 */
class CharacterDataToCharacterMapper {

    fun map(from: List<CharactersData.CharacterDetailsData>): List<Character> {
        return from.map {
            Character(
                id = it.id.toInt(),
                image = it.image,
                name = it.name,
                species = it.species,
                gender = it.gender,
                status = it.status,
            )
        }
    }
}
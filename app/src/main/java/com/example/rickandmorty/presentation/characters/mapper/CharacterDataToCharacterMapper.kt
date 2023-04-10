package com.example.rickandmorty.presentation.characters.mapper

import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.presentation.characters.Character

/**
 * Преобразует данные из 1 вида датакласса в другой, необходимый нам
 */
class CharacterDataToCharacterMapper {

    fun map(from: List<CharactersData.SingleCharacterData>): List<Character> {
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
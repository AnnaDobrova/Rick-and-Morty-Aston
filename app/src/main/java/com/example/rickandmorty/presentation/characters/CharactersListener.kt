package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.data.characters.model.CharactersData

/**
 * Слушатель который слушает изменения, и при произошедших изменениях, берет данные(изменные и возвращает
 * туда где он реализован
 */
interface CharactersListener {
    fun getAllCharacters(characters: List<CharactersData.CharacterDetailsData>)
}
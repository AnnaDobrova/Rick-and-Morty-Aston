package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.data.characters.model.CharactersData

interface CharacterDetailsListener {
    fun getCharacterById(character: CharactersData.CharacterDetailsData)
}
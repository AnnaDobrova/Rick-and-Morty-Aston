package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

interface CharacterListDetailsListener {
    fun goToDetailsCharacter(character: SingleCharacterUi)
    fun goToDetailsCharacter(character: CharacterDetailUi)
}
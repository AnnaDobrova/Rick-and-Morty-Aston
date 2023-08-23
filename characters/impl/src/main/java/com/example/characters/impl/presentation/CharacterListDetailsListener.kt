package com.example.characters.impl.presentation

import com.example.characters.impl.presentation.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

interface CharacterListDetailsListener {
    fun goToDetailsCharacter(character: SingleCharacterUi)
    fun goToDetailsCharacter(character: CharacterDetailUi)
}
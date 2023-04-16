package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

interface CharacterListDetailsListener {
    fun goToDetailsCharacter(singleCharacterUi: SingleCharacterUi)
}
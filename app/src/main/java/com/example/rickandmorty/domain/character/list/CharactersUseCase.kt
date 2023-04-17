package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.presentation.characters.list.CharacterListFromDomainToUiCallback

interface CharactersUseCase {
    fun registerFromDomainToUiCallback(callback: CharacterListFromDomainToUiCallback)
    fun loadAllCharacters()
}
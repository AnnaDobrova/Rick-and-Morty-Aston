package com.example.rickandmorty.presentation.characters.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharacterListFromDomainToUiCallback {
    fun getAllCharacters(characterList: List<SingleCharacterDomain>)
}

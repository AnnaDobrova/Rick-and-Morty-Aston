package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharacterListFromDataToDomainCallback {
    fun getAllCharacters(charactersList: List<SingleCharacterDomain>)
}

package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharacterListFromDataToDomainCallback {
    fun getAllCharactersFromDataToDomain(charactersList: List<SingleCharacterDomain>)
}

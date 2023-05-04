package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharactersUseCase {
    suspend fun getAllCharacters(): List<SingleCharacterDomain>
}
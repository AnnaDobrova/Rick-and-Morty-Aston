package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharactersRepository {
    suspend fun getAllCharacters(): List<SingleCharacterDomain>
}
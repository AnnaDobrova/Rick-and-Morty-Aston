package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<AnnaResponse<List<SingleCharacterDomain>>>
    fun getAllCharactersFromLocal(): Flow<List<SingleCharacterDomain>>
}
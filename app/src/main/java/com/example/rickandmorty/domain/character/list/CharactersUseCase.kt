package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {
    fun getAllCharacters(): Flow<AnnaResponse<List<SingleCharacterDomain>>>
    fun getAllCharactersFromLocal(): Flow<AnnaResponse<List<SingleCharacterDomain>>>
}
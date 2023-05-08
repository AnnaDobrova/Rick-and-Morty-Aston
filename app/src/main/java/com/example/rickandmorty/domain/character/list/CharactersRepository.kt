package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse

interface CharactersRepository {
    suspend fun getAllCharacters(): AnnaResponse<List<SingleCharacterDomain>>
    suspend fun getAllCharactersFromLocal(): AnnaResponse<List<SingleCharacterDomain>>
}
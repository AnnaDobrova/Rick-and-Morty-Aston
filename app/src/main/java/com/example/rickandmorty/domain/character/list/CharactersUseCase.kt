package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse

interface CharactersUseCase {
    suspend fun getAllCharacters(): AnnaResponse<List<SingleCharacterDomain>>
}
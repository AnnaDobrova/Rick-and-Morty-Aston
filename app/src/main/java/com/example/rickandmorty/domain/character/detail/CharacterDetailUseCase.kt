package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface CharacterDetailUseCase {
    fun loadCharacterById(id: Int): Flow<AnnaResponse<CharacterDetailDomain>>
    fun loadCharacterByIdFromLocal(id: Int): Flow<AnnaResponse<CharacterDetailDomain>>
}

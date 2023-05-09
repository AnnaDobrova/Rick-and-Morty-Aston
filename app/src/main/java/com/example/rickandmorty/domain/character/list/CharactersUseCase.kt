package com.example.rickandmorty.domain.character.list

import androidx.paging.PagingData
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {
    fun getAllCharacters(): Flow<PagingData<SingleCharacterDomain>>
    fun getAllCharactersFromLocal(): Flow<PagingData<SingleCharacterDomain>>
}
package com.example.rickandmorty.domain.character.list

import androidx.paging.PagingData
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : CharactersUseCase {

    override fun getAllCharacters(): Flow<PagingData<SingleCharacterDomain>> {
        return charactersRepository.getAllCharacters()
    }

    override fun getAllCharactersFromLocal(): Flow<PagingData<SingleCharacterDomain>> {
        return charactersRepository.getAllCharactersFromLocal()
    }
}
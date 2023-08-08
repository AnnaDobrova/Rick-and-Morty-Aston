package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : CharactersUseCase {

    override fun getAllCharacters(): Flow<AnnaResponse<List<SingleCharacterDomain>>> {
        return charactersRepository.getAllCharacters()
    }

    override fun getAllCharactersFromLocal(): Flow<AnnaResponse<List<SingleCharacterDomain>>> {
        return charactersRepository.getAllCharactersFromLocal()
    }
}
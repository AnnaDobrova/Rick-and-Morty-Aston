package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class CharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : CharactersUseCase {

    override suspend fun getAllCharacters(): AnnaResponse<List<SingleCharacterDomain>> {
        return charactersRepository.getAllCharacters()
    }
}
package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import javax.inject.Inject

class CharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : CharactersUseCase {

    override suspend fun getAllCharacters(): List<SingleCharacterDomain> {
        return charactersRepository.getAllCharacters()
    }
}
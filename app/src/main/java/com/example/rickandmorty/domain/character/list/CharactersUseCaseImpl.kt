package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.data.characters.list.CharactersRepositoryImpl
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

class CharactersUseCaseImpl : CharactersUseCase {

    private var charactersRepository: CharactersRepository? = null

    init {
        charactersRepository = CharactersRepositoryImpl()
    }

    override fun loadAllCharacters(): List<SingleCharacterDomain> {
        return charactersRepository?.loadAllCharacters() ?: emptyList()
    }
}
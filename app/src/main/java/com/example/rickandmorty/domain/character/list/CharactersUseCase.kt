package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

interface CharactersUseCase {
    fun loadAllCharacters(): List<SingleCharacterDomain>
}
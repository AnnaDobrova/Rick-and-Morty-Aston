package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.presentation.characters.detail.CharacterDetailFromDomainToUiCallback

interface CharacterDetailUseCase {
    fun registerFromDomainToUiCallback(callback: CharacterDetailFromDomainToUiCallback)
    fun loadCharacterById(id: Int)
}

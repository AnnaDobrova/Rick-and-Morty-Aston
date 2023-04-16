package com.example.rickandmorty.presentation.characters.detail

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain

interface CharacterDetailFromDomainToUiCallback {
    fun getCharacterDetail(characterDetail: CharacterDetailDomain)
}

package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain

interface CharacterDetailFromDataToDomainCallback {
    fun getCharacterById(characterDetail: CharacterDetailDomain)
}

package com.example.rickandmorty.domain.character.detail

interface CharacterDetailRepository {
    fun loadCharacterById(id: Int)
}
package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.character.list.Character

interface CharactersDetailsListener {
    fun goToDetails(character: Character)
}
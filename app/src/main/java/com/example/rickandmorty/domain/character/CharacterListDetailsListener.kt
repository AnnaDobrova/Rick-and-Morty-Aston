package com.example.rickandmorty.domain.character

import com.example.rickandmorty.domain.character.list.Character

interface CharacterListDetailsListener {
    fun goToDetailsCharacter(character: Character)
}
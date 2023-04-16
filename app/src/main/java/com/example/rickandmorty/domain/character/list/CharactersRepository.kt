package com.example.rickandmorty.domain.character.list

interface CharactersRepository {
    fun registerFromDataToDomainCallback(callback: CharacterListFromDataToDomainCallback)
    fun loadAllCharacters()
}
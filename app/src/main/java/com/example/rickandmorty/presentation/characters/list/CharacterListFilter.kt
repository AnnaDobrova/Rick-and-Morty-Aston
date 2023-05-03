package com.example.rickandmorty.presentation.characters.list

interface CharacterListFilter {

    fun filterStatus(selected: String)

    fun filterSpecies(selected: String)

    fun filterGender(selected: String)
}
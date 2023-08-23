package com.example.rickandmorty.presentation.characters.list.model

data class SingleCharacterUi(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String
)
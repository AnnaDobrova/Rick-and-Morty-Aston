package com.example.rickandmorty.domain.character

data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String
)
package com.example.rickandmorty.presentation.locations.list.model

data class SingleLocationUI(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val listCharactersInLocation: List<String>,
    val url: String,
    val created: String
)

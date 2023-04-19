package com.example.rickandmorty.presentation.locations.detail.model

data class LocationDetailUi(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val listCharactersInLocation: List<String>,
    val url: String,
    val created: String)
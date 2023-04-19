package com.example.rickandmorty.domain.location.list.model


data class SingleLocationDomain(
    val id: Int,
    val nameLocation: String,
    val typeLocation : String,
    val dimensionLocation: String,
    val listCharactersInLocation: List<String>,
    val url: String,
    val created: String
)

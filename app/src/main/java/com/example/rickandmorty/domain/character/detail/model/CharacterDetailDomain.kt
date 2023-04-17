package com.example.rickandmorty.domain.character.detail.model

import java.io.Serializable

data class CharacterDetailDomain(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterDetailOriginDomain,
    val location: CharacterDetailLocationDomain,
    val image: String,
    val episodes: List<String>,
    val url: String,
    val created: String
) : Serializable
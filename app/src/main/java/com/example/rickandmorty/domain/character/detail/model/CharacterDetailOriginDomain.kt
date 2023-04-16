package com.example.rickandmorty.domain.character.detail.model

import java.io.Serializable

data class CharacterDetailOriginDomain(
    val name: String,
    val url: String
) : Serializable
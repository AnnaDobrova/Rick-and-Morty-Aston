package com.example.rickandmorty.domain.character.list.model

import java.io.Serializable

data class SingleCharacterLocationDomain(
    val name: String,
    val url: String
) : Serializable
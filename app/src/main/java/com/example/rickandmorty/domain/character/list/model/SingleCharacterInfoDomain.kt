package com.example.rickandmorty.domain.character.list.model

import java.io.Serializable

data class SingleCharacterInfoDomain(
    val count: Long,
    val pages: Long,
    val next: String,
    val previous: String
) : Serializable
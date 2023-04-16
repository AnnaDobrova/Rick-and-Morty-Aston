package com.example.rickandmorty.domain.character.detail.model

import java.io.Serializable

data class CharacterDetailInfoDomain(
    val count: Long,
    val pages: Long,
    val next: String,
    val previous: String
) : Serializable
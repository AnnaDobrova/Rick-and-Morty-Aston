package com.example.rickandmorty.domain.character.list.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class SingleCharacterOriginDomain(
    val name: String,
    val url: String
) : Serializable
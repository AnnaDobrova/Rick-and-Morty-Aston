package com.example.rickandmorty.domain.character.list.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SingleCharacterDomain(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: SingleCharacterOriginDomain,
    val location: SingleCharacterLocationDomain,
    val image: String,
    val episodes: List<String>,
    val url: String,
    val created: String
) : Serializable
package com.example.rickandmorty.data.characters.detail.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDetailData(
    @Expose
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val status: String,
    @Expose
    val species: String,
    @Expose
    val type: String,
    @Expose
    val gender: String,
    @Expose
    val origin: CharacterDetailOriginData,
    @Expose
    val location: CharacterDetailLocationData,
    @Expose
    val image: String,
    @SerializedName("episode")
    @Expose
    val episodes: List<String>,
    @Expose
    val url: String,
    @Expose
    val created: String
)
package com.example.rickandmorty.data.characters.detail.model

import com.google.gson.annotations.Expose

data class CharacterDetailLocationData(
    @Expose
    val name: String,
    @Expose
    val url: String
)
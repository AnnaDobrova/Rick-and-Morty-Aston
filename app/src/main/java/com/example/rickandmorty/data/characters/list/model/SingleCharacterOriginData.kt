package com.example.rickandmorty.data.characters.list.model

import com.google.gson.annotations.Expose

data class SingleCharacterOriginData(
    @Expose
    val name: String,
    @Expose
    val url: String
)
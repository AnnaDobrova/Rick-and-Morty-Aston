package com.example.rickandmorty.data.characters.detail.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDetailInfoData(
    @Expose
    val count: Long,
    @Expose
    val pages: Long,
    @Expose
    val next: String,
    @SerializedName("prev")
    @Expose
    val previous: String
)
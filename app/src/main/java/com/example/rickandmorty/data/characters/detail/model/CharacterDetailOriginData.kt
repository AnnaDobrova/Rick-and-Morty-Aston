package com.example.rickandmorty.data.characters.detail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity (tableName = "CharacterDetailOriginEntity")
data class CharacterDetailOriginData(
    @Expose
    @PrimaryKey
    val name: String,
    @Expose
    val url: String
)
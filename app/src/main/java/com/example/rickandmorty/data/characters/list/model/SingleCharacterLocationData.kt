package com.example.rickandmorty.data.characters.list.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "CharacterLocationEntity")
data class SingleCharacterLocationData(
    @Expose
    @PrimaryKey
    val name: String,
    @Expose
    val url: String
)
package com.example.rickandmorty.data.local.characters.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterLocationEntity")
data class CharacterLocationEntity(
    @PrimaryKey var name: String,
    var url: String
)
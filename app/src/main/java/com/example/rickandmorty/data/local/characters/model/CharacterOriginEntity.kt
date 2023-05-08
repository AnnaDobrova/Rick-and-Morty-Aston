package com.example.rickandmorty.data.local.characters.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterOriginEntity")
data class CharacterOriginEntity(
    @PrimaryKey val name: String,
    val url: String
)
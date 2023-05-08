package com.example.rickandmorty.data.characters.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterLocationEntity")
data class CharacterLocationEntity(
    @PrimaryKey var name: String,
    var url: String
)
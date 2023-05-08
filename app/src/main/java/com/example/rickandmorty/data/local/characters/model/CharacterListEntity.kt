package com.example.rickandmorty.data.local.characters.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterListEntity")
data class CharacterListEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @ColumnInfo("origin") val origin: CharacterOriginEntity,
    @ColumnInfo("location") val location: CharacterLocationEntity,
    val image: String,
    @ColumnInfo("episode") val episodes: List<String>,
    val url: String,
    val created: String
)
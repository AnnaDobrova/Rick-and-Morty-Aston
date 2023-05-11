package com.example.rickandmorty.data.characters.detail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity (tableName = "CharacterDetailEntity")
data class CharacterDetailData(
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    val characterDetailId: Long,
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
package com.example.rickandmorty.data.characters.list.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CharacterListEntity") //local
data class SingleCharacterData(
    @Expose
    @PrimaryKey(autoGenerate = true) //local
    val id: Long,
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
    @ColumnInfo("origin") //local
    val origin: SingleCharacterOriginData,
    @Expose
    @ColumnInfo("location") //local
    val location: SingleCharacterLocationData,
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
package com.example.rickandmorty.data.locations.list.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "LocationListEntity")
data class SingleLocationData(
    @Expose
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val type: String,
    @Expose
    val dimension: String,
    @SerializedName("residents")
    @Expose
    val listCharactersInLocation: List<String>,
    @Expose
    val url: String,
    @Expose
    val created: String
)
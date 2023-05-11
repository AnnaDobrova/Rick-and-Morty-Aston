package com.example.rickandmorty.data.episodes.detail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SingleEpisodeDetailEntity")
data class SingleEpisodeDetailData(
    @Expose
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val air_date: String,
    @Expose
    val episode: String,
    @Expose
    val characters: List<String>,
    @Expose
    val url: String,
    @Expose
    val created: String
)
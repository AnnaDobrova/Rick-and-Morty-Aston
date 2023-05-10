package com.example.rickandmorty.data.episodes.list.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "EpisodeListEntity")
data class SingleEpisodeListData(
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
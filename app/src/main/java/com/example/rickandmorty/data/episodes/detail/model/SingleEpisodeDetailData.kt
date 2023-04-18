package com.example.rickandmorty.data.episodes.detail.model

import com.google.gson.annotations.Expose

data class SingleEpisodeDetailData (
    @Expose
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
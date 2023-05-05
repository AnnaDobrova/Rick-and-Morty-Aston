package com.example.rickandmorty.presentation.episodes.list.model

data class SingleEpisodeUI(
    val id: Int,
    val nameEpisode: String,
    val numberEpisode: String,
    val airDataEpisode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

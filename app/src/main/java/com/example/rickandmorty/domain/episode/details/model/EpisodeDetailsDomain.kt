package com.example.rickandmorty.domain.episode.details.model

data class EpisodeDetailsDomain(
    val id: Int,
    val nameEpisode: String,
    val numberEpisode: String,
    val airDataEpisode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

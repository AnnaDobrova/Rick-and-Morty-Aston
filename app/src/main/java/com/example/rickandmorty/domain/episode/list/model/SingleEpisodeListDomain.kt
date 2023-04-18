package com.example.rickandmorty.domain.episode.list.model

import java.io.Serializable

data class SingleEpisodeListDomain(
    val id: Int,
    val nameEpisode: String,
    val numberEpisode: String,
    val airDataEpisode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Serializable

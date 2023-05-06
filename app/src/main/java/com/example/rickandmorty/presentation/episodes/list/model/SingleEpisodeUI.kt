package com.example.rickandmorty.presentation.episodes.list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SingleEpisodeUI(
    val id: Int,
    val nameEpisode: String,
    val numberEpisode: String,
    val airDataEpisode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Parcelable

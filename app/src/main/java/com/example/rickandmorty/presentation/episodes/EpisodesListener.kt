package com.example.rickandmorty.presentation.episodes

import com.example.rickandmorty.data.episodes.model.EpisodesData

interface EpisodesListener {

    fun getAllEpisodes(episode: List<EpisodesData.SingleEpisodeData>)
}
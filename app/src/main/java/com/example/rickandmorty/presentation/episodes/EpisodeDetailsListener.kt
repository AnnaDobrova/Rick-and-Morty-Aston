package com.example.rickandmorty.presentation.episodes

import com.example.rickandmorty.data.episodes.model.EpisodesData

interface EpisodeDetailsListener {

    fun getEpisodeById(episode: EpisodesData.SingleEpisodeData)
}
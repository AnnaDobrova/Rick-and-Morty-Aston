package com.example.rickandmorty.domain.episode

import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

interface EpisodeListDetailsListener {
    fun goToDetailsEpisode(episode: SingleEpisodeUI)
    fun goToDetailsEpisodeString(episodeString: String)

}
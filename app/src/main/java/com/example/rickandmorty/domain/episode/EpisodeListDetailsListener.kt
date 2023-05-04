package com.example.rickandmorty.domain.episode

import com.example.rickandmorty.presentation.list.model.SingleEpisodeUI

interface EpisodeListDetailsListener {
    fun goToDetailsEpisode(episode: SingleEpisodeUI)
    fun goToDetailsEpisodeString(episodeString: String)

}
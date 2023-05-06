package com.example.rickandmorty.domain.episode

import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

interface EpisodeListDetailsListener {
    fun goToDetailsEpisode(episode: EpisodeDetailUi)
    fun goToDetailsEpisode(episode: SingleEpisodeUI)
    fun goToDetailsEpisodeString(episodeString: String)

}
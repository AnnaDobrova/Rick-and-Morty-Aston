package com.example.rickandmorty.domain.episode

import com.example.rickandmorty.domain.episode.list.Episode

interface EpisodeListDetailsListener {
    fun goToDetailsEpisode(episode: Episode)
}
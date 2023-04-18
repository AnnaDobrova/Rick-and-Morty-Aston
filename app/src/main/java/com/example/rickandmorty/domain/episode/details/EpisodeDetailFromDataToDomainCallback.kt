package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain

interface EpisodeDetailFromDataToDomainCallback {
    fun getEpisodeDetail(episodeDetail: EpisodeDetailsDomain)
}
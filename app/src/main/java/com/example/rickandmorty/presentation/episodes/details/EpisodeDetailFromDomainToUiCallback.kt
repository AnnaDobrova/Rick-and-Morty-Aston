package com.example.rickandmorty.presentation.episodes.details

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain

interface EpisodeDetailFromDomainToUiCallback {
    fun getEpisodeDetail(EpisodeDetail: EpisodeDetailsDomain)
}
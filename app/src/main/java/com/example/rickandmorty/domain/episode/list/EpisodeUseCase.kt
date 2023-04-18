package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback

interface EpisodeUseCase {
    fun resisterFromDataToDomainCallback(callback: EpisodeListFromDomainToUiCallback)
    fun loadAllEpisodes()
}

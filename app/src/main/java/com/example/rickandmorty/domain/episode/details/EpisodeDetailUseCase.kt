package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.presentation.list.EpisodeListFromDomainToUiCallback

interface EpisodeDetailUseCase {
    fun loadEpisodeById ( int: Int)
    fun registerFromDataToDomainCallback(callback: EpisodeDetailFromDataToDomainCallback)
}
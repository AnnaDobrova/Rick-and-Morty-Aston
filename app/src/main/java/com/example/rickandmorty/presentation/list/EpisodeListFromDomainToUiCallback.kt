package com.example.rickandmorty.presentation.list

import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

interface EpisodeListFromDomainToUiCallback {

    fun getAllEpisodesFromDomainToUI(episodeList: List<SingleEpisodeListDomain>)
}
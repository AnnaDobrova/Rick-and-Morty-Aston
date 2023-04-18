package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

interface EpisodeListFromDataToDomainCallBack {
    fun getAllEpisodesFromDataToDomain(episodeList: List<SingleEpisodeListDomain>)
}
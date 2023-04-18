package com.example.rickandmorty.domain.episode.list

interface EpisodesRepository {
    fun resisterFromDataToDomainCallback( callback: EpisodeListFromDataToDomainCallBack)
    fun loadAllEpisodes()
}
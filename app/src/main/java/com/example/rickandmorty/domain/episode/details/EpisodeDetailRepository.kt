package com.example.rickandmorty.domain.episode.details

interface EpisodeDetailRepository {
    fun loadEpisodeById (id: Int)
    fun registerFromDataToDomainCallback(callback: EpisodeDetailFromDataToDomainCallback)
}
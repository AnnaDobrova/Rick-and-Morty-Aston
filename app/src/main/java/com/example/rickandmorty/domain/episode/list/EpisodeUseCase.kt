package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

interface EpisodeUseCase {
    suspend fun getAllEpisodes(): List<SingleEpisodeListDomain>
}

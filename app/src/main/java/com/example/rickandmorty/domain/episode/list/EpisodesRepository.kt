package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

interface EpisodesRepository {
   suspend fun getAllEpisodes(): List<SingleEpisodeListDomain>
}
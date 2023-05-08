package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse

interface EpisodesRepository {
   suspend fun getAllEpisodes(): AnnaResponse<List<SingleEpisodeListDomain>>
}
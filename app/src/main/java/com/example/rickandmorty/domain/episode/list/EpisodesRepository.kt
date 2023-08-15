package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getAllEpisodes(): Flow<AnnaResponse<List<SingleEpisodeListDomain>>>
    fun getEpisodeFromLocal(): Flow<List<SingleEpisodeListDomain>>

}
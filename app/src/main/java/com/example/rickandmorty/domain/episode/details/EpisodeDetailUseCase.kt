package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailUseCase {
    fun loadEpisodeById ( int: Int): Flow<AnnaResponse<EpisodeDetailsDomain>>
}
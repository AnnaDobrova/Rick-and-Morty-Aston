package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface EpisodeUseCase {
    fun getAllEpisodes(): Flow<AnnaResponse<List<SingleEpisodeListDomain>>>
    fun getAllEpisodeFromLocal(): Flow<List<SingleEpisodeListDomain>>
}

package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val episodeRepository: EpisodesRepository
) : EpisodeUseCase {

    override fun getAllEpisodes(): Flow<AnnaResponse<List<SingleEpisodeListDomain>>> {
        return episodeRepository.getAllEpisodes()
    }

    override fun getAllEpisodeFromLocal(): Flow<List<SingleEpisodeListDomain>> {
        return episodeRepository.getEpisodeFromLocal()
    }
}
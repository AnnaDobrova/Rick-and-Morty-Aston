package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val episodeRepository: EpisodesRepository
) : EpisodeUseCase {

    override suspend fun getAllEpisodes(): AnnaResponse<List<SingleEpisodeListDomain>> {
        return episodeRepository.getAllEpisodes()
    }

    override suspend fun getAllEpisodeFromLocal(): AnnaResponse<List<SingleEpisodeListDomain>> {
        return episodeRepository.getEpisodeFromLocal()
    }
}
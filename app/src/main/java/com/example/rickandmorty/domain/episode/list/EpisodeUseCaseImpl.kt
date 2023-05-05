package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val episodeRepository: EpisodesRepository
) : EpisodeUseCase {

    override suspend fun getAllEpisodes(): List<SingleEpisodeListDomain> {
        return episodeRepository.getAllEpisodes()
    }
}
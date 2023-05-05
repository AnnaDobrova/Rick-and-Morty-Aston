package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeDetailUseCaseImpl @Inject constructor(
    private val episodeDetailsRepository: EpisodeDetailRepository
) : EpisodeDetailUseCase {

    override fun loadEpisodeById(int: Int): Flow<AnnaResponse<EpisodeDetailsDomain>>{
     return   episodeDetailsRepository.loadEpisodeById(int)
    }
}
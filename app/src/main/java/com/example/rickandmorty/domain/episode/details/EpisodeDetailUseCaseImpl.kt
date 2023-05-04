package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import javax.inject.Inject

class EpisodeDetailUseCaseImpl @Inject constructor(
    private val episodeDetailsRepository: EpisodeDetailRepository
) : EpisodeDetailUseCase, EpisodeDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: EpisodeDetailFromDataToDomainCallback? = null

    init {
        episodeDetailsRepository.registerFromDataToDomainCallback(this)
    }

    override fun getEpisodeDetail(episodeDetail: EpisodeDetailsDomain) {
        callbackFromDomainToUiCallback?.getEpisodeDetail(episodeDetail)
    }

    override fun loadEpisodeById(int: Int) {
        episodeDetailsRepository.loadEpisodeById(int)
    }

    override fun registerFromDataToDomainCallback(callback: EpisodeDetailFromDataToDomainCallback) {
        callbackFromDomainToUiCallback = callback
    }

}
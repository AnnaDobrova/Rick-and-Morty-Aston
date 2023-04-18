package com.example.rickandmorty.domain.episode.details

import com.example.rickandmorty.data.episodes.detail.EpisodeDetailsRepositoryImpl
import com.example.rickandmorty.data.episodes.list.EpisodeRepositoryImpl
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.domain.episode.list.EpisodeUseCaseImpl
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback

class EpisodeDetailUseCaseImpl : EpisodeDetailUseCase, EpisodeDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: EpisodeDetailFromDataToDomainCallback? = null
    private val episodeDetailsRepository: EpisodeDetailsRepositoryImpl by lazy {
        EpisodeDetailsRepositoryImpl()
    }

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
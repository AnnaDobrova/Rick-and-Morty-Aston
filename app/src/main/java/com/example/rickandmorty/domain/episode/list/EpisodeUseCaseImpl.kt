package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.list.EpisodeListFromDomainToUiCallback
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val episodeRepository: EpisodesRepository
) : EpisodeUseCase, EpisodeListFromDataToDomainCallBack {


    private var callFromDomainToUI: EpisodeListFromDomainToUiCallback? = null

    init {
        episodeRepository.resisterFromDataToDomainCallback(this)
    }

    override fun resisterFromDataToDomainCallback(callback: EpisodeListFromDomainToUiCallback) {
        this.callFromDomainToUI = callback
    }

    override fun loadAllEpisodes() {
        episodeRepository.loadAllEpisodes()
    }

    override fun getAllEpisodesFromDataToDomain(episodeList: List<SingleEpisodeListDomain>) {
        callFromDomainToUI?.getAllEpisodesFromDomainToUI(episodeList)
    }

}
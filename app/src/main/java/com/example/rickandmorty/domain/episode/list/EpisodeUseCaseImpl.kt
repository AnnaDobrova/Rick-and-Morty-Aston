package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.data.episodes.list.EpisodeRepositoryImpl
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback

class EpisodeUseCaseImpl : EpisodeUseCase, EpisodeListFromDataToDomainCallBack {

    private val episodeRepository: EpisodesRepository by lazy {
        EpisodeRepositoryImpl()
    }

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
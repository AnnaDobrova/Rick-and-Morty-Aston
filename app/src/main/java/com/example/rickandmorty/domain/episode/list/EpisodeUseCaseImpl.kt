package com.example.rickandmorty.domain.episode.list

import com.example.rickandmorty.data.episodes.list.EpisodeRepositoryImpl
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback

class EpisodeUseCaseImpl: EpisodeUseCase, EpisodeListFromDomainToUiCallback {

    private val episodeRepository: EpisodesRepository by lazy {
        EpisodeRepositoryImpl()
    }

    override fun getAllEpisodesFromDomainToUI(episodeList: List<SingleEpisodeListDomain>) {
        TODO("Not yet implemented")
    }

    override fun resisterFromDataToDomainCallback(callback: EpisodeListFromDomainToUiCallback) {
        TODO("Not yet implemented")
    }

    override fun loadAllEpisodes() {
        TODO("Not yet implemented")
    }

}
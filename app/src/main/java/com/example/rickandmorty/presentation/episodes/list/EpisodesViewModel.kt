package com.example.rickandmorty.presentation.episodes.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.domain.episode.list.EpisodeUseCaseImpl
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

class EpisodesViewModel : ViewModel(), EpisodeListFromDomainToUiCallback {

    private val episodes = MutableLiveData<List<SingleEpisodeUI>>(emptyList())

    private var episodesUseCase: EpisodeUseCase? = null
    private val mapperFromDomainToUi by lazy {
        SingleEpisodeDomainToSingleEpisodeUiMapper()
    }

    init {
        episodesUseCase = EpisodeUseCaseImpl()
        episodesUseCase?.resisterFromDataToDomainCallback(this)
        loadAllEpisodes()
    }

    override fun getAllEpisodesFromDomainToUI(episodeList: List<SingleEpisodeListDomain>) {
        episodes.postValue(
            mapperFromDomainToUi.map(episodeList)
        )
    }

    fun getAllEpisodes(): LiveData<List<SingleEpisodeUI>> = episodes

    fun loadAllEpisodes() {
        episodesUseCase?.loadAllEpisodes()
    }
}
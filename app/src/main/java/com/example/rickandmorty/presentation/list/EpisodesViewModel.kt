package com.example.rickandmorty.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.list.model.SingleEpisodeUI
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesUseCase: EpisodeUseCase,
    private val mapperFromDomainToUi: SingleEpisodeDomainToSingleEpisodeUiMapper
): ViewModel(), EpisodeListFromDomainToUiCallback {

    private val episodes = MutableLiveData<List<SingleEpisodeUI>>(emptyList())


    init {
        episodesUseCase.resisterFromDataToDomainCallback(this)
        loadAllEpisodes()
    }

    override fun getAllEpisodesFromDomainToUI(episodeList: List<SingleEpisodeListDomain>) {
        episodes.postValue(
            mapperFromDomainToUi.map(episodeList)
        )
    }

    fun getAllEpisodes(): LiveData<List<SingleEpisodeUI>> = episodes

    fun loadAllEpisodes() {
        episodesUseCase.loadAllEpisodes()
    }
}
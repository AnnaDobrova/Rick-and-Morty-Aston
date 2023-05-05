package com.example.rickandmorty.presentation.episodes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesUseCase: EpisodeUseCase,
    private val mapperFromDomainToUi: SingleEpisodeDomainToSingleEpisodeUiMapper
) : ViewModel() {

    private val episodes = MutableLiveData<List<SingleEpisodeUI>>(emptyList())

    init {
        loadAllEpisodes()
    }

    fun loadAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodes.postValue(mapperFromDomainToUi.map(episodesUseCase.getAllEpisodes()))
        }
    }

    fun getAllEpisodes(): LiveData<List<SingleEpisodeUI>> = episodes
}
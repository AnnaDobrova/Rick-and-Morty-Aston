package com.example.rickandmorty.presentation.episodes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesUseCase: EpisodeUseCase,
    private val mapperFromDomainToUi: SingleEpisodeDomainToSingleEpisodeUiMapper
) : ViewModel() {

    private val episodes = MutableLiveData<List<SingleEpisodeUI>>(emptyList())

    private val error = MutableLiveData<String>()

    init {
        loadAllEpisodes()
    }

    fun loadAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = episodesUseCase.getAllEpisodes()) {
                is AnnaResponse.Success -> episodes.postValue(mapperFromDomainToUi.map(response.data))
                is AnnaResponse.Failure -> {
                    error.postValue(response.error.message)
                    loadAllEpisodeFromLocal()
                }
            }
        }
    }

    private fun loadAllEpisodeFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = episodesUseCase.getAllEpisodeFromLocal()) {
                is AnnaResponse.Success -> episodes.postValue(mapperFromDomainToUi.map(response.data))
                is AnnaResponse.Failure -> {
                    error.postValue(response.error.message)
                }
            }
        }
    }

    fun getAllEpisodes(): LiveData<List<SingleEpisodeUI>> = episodes
    fun getError(): LiveData<String> = error
}
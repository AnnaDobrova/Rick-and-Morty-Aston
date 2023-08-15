package com.example.rickandmorty.presentation.episodes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episode.list.EpisodeUseCase
import com.example.rickandmorty.presentation.episodes.list.mapper.SingleEpisodeDomainToSingleEpisodeUiMapper
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.Connectivity
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val episodesUseCase: EpisodeUseCase,
    private val mapperFromDomainToUi: SingleEpisodeDomainToSingleEpisodeUiMapper,
    private val connectivity: Connectivity
) : ViewModel() {

    private val episodes = MutableStateFlow<ViewState<List<SingleEpisodeUI>>>(ViewState.Loading)

    init {
        load()
    }

    private fun loadAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodesUseCase.getAllEpisodes().collect { annaResponse ->
                episodes.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure -> {
                        ViewState.Error(annaResponse.error)
                    }
                }
            }
        }
    }

    private fun loadAllEpisodeFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            episodesUseCase.getAllEpisodeFromLocal().collect { data ->
                episodes.value = ViewState.Data(mapperFromDomainToUi.map(data))
            }
        }
    }

    fun load() {
        if (connectivity.isNetworkAvailable()) {
            loadAllEpisodes()
        } else {
            episodes.value = ViewState.Error(Throwable("Отсутствует интернет соединение"))
            loadAllEpisodeFromLocal()
        }
    }

    fun getAllEpisodes(): MutableStateFlow<ViewState<List<SingleEpisodeUI>>> = episodes
}

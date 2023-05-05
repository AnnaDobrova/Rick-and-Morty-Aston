package com.example.rickandmorty.presentation.episodes.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private var episodeDetailUseCase: EpisodeDetailUseCase,
    private val mapperFromDomainToUi: EpisodeDetailDomainToEpisodeDetailUiMapper
) : ViewModel() {

    private var episodes = MutableLiveData<EpisodeDetailUi>()

    fun loadEpisodeById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            episodeDetailUseCase.loadEpisodeById(id).collect() { annaResponse ->
                when(annaResponse){
                    is AnnaResponse.Success ->{
                        episodes.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }
                    is AnnaResponse.Failure -> { Throwable(annaResponse.error)}
                }
            }
        }
    }

    fun getEpisode(): LiveData<EpisodeDetailUi> = episodes
}
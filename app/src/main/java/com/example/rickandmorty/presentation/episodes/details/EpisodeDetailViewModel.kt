package com.example.rickandmorty.presentation.episodes.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.episode.details.EpisodeDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private var episodeDetailUseCase: EpisodeDetailUseCase,
    private val mapperFromDomainToUi: EpisodeDetailDomainToEpisodeDetailUiMapper
) : ViewModel(), EpisodeDetailFromDataToDomainCallback {

    private var episodes = MutableLiveData<EpisodeDetailUi>()

    init {
        episodeDetailUseCase.registerFromDataToDomainCallback(this@EpisodeDetailViewModel)
    }

    override fun getEpisodeDetail(episodeDetail: EpisodeDetailsDomain) {
        episodes.postValue(mapperFromDomainToUi.map(episodeDetail))
    }

    fun loadEpisodeById(id: Int) {
        episodeDetailUseCase.loadEpisodeById(id)
    }

    fun getEpisode(): LiveData<EpisodeDetailUi> = episodes

}
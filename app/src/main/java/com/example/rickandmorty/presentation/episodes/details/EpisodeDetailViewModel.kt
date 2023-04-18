package com.example.rickandmorty.presentation.episodes.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.episode.details.EpisodeDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCaseImpl
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi

class EpisodeDetailViewModel : ViewModel(), EpisodeDetailFromDataToDomainCallback {

    private var episodes = MutableLiveData<EpisodeDetailUi>()

    private var episodeDetailUseCase: EpisodeDetailUseCase? = null
    private val mapperFromDomainToUi by lazy {
        EpisodeDetailDomainToEpisodeDetailUiMapper()
    }

    init {
        episodeDetailUseCase = EpisodeDetailUseCaseImpl()
        episodeDetailUseCase?.registerFromDataToDomainCallback(this@EpisodeDetailViewModel)
    }

    override fun getEpisodeDetail(episodeDetail: EpisodeDetailsDomain) {
        episodes.postValue(mapperFromDomainToUi.map(episodeDetail))
    }

    fun loadEpisodeById(id: Int) {
        episodeDetailUseCase?.loadEpisodeById(id)
    }

    fun getEpisode(): LiveData<EpisodeDetailUi> = episodes

}
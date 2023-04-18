package com.example.rickandmorty.presentation.episodes

import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

interface EpisodeDetailsListener {

    fun getEpisodeById(episode: SingleEpisodeDetailData)
}
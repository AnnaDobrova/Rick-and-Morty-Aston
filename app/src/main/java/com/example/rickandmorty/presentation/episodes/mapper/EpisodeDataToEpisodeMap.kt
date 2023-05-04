package com.example.rickandmorty.presentation.episodes.mapper

import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.presentation.list.model.SingleEpisodeUI

class EpisodeDataToEpisodeMap {

    fun map(from: List<SingleEpisodeListData>): List<Episode> {
        return from.map {
            Episode(
                id = it.id,
                nameEpisode = it.name,
                numberEpisode = it.episode,
                airDataEpisode = it.air_date
            )
        }
    }
}
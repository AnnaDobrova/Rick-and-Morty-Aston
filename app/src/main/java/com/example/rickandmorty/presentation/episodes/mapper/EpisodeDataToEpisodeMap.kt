package com.example.rickandmorty.presentation.episodes.mapper

import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.domain.episode.list.Episode

class EpisodeDataToEpisodeMap {

    fun map(from: List<EpisodesData.SingleEpisodeData>): List<Episode> {
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
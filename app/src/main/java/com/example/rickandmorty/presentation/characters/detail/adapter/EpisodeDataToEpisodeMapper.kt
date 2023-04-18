package com.example.rickandmorty.presentation.characters.detail.adapter

import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.domain.episode.list.model.Episode

class EpisodeDataToEpisodeMapper {

    fun map(from: SingleEpisodeDetailData): Episode = Episode(
        id = from.id,
        nameEpisode = from.name,
        numberEpisode = from.episode,
        airDataEpisode = from.air_date
    )
}

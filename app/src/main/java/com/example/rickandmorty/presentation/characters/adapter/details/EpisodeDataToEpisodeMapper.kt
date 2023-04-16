package com.example.rickandmorty.presentation.characters.adapter.details

import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.domain.episode.list.Episode

class EpisodeDataToEpisodeMapper {

    fun map(from: EpisodesData.SingleEpisodeData): Episode = Episode(
        id = from.id,
        nameEpisode = from.name,
        numberEpisode = from.episode,
        airDataEpisode = from.air_date
    )
}

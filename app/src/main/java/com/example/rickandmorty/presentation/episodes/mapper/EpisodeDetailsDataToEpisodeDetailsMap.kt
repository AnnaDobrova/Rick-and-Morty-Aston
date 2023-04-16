package com.example.rickandmorty.presentation.episodes.mapper

import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetails

class EpisodeDetailsDataToEpisodeDetailsMap {

    fun map (from: EpisodesData.SingleEpisodeData) = EpisodeDetails(
        id = from.id,
        nameEpisode = from.name,
        numberEpisode = from.episode,
        airDataEpisode = from.air_date,
        characters = from.characters,
        url = from.url,
        created = from.created
    )
}
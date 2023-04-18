package com.example.rickandmorty.data.episodes.detail.mapper

import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain

class EpisodeDetailDataToEpisodeDetailDomainMapper {

    fun map(from: SingleEpisodeDetailData) =
        EpisodeDetailsDomain(
            id = from.id,
            nameEpisode = from.name,
            numberEpisode = from.episode,
            airDataEpisode = from.air_date,
            characters = from.characters,
            url = from.url,
            created = from.created
        )
}
package com.example.rickandmorty.presentation.episodes.details.mapper

import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi

class EpisodeDetailDomainToEpisodeDetailUiMapper {

    fun map(from: EpisodeDetailsDomain) = EpisodeDetailUi(
        id = from.id,
        nameEpisode = from.nameEpisode,
        numberEpisode = from.numberEpisode,
        airDataEpisode = from.airDataEpisode,
        characters = from.characters,
        url = from.url,
        created = from.created
    )
}
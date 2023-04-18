package com.example.rickandmorty.data.episodes.list.mapper

import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain

class EpisodeDataToListSingleEpisodeDomainMapper {

    fun map(from: List< SingleEpisodeListData>) =
        from.map {
            SingleEpisodeListDomain(
                id = it.id,
                nameEpisode = it.name,
                numberEpisode = it.episode,
                airDataEpisode = it.air_date,
                characters = it.characters,
                url = it.url,
                created = it.created
            )
        }


}
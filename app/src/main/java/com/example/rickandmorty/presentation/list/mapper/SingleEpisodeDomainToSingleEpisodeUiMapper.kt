package com.example.rickandmorty.presentation.list.mapper

import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.presentation.list.model.SingleEpisodeUI

class SingleEpisodeDomainToSingleEpisodeUiMapper {

    fun map(from: List<SingleEpisodeListDomain>): List<SingleEpisodeUI> {
        return from.map {
            SingleEpisodeUI(
                id = it.id,
                nameEpisode = it.nameEpisode,
                numberEpisode = it.numberEpisode,
                airDataEpisode = it.airDataEpisode,
                characters = it.characters,
                url = it.url,
                created = it.created
            )
        }
    }
}
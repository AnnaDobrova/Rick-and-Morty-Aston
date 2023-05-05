package com.example.rickandmorty.data.episodes.list

import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.tryMapSuspended
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodeNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDataToListSingleEpisodeDomainMapper
) : EpisodesRepository {

    override suspend fun getAllEpisodes(): List<SingleEpisodeListDomain> {
        return mapperFromDataToDomain.map(
            episodeDetailsNetworkDataSours.getAllEpisodes().body()?.episodes ?: emptyList()
        )
    }

}
package com.example.rickandmorty.data.episodes.list

import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.data.local.list.episodes.EpisodeLocalDao
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodeNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDataToListSingleEpisodeDomainMapper,
    private val episodeLocalDao: EpisodeLocalDao
) : EpisodesRepository {

    override suspend fun getAllEpisodes(): AnnaResponse<List<SingleEpisodeListDomain>> {
        return try {
            val response = episodeDetailsNetworkDataSours.getAllEpisodes()
            episodeLocalDao.setEpisodeListData(response.body()?.episodes ?: emptyList())
            AnnaResponse.Success(
                mapperFromDataToDomain.map(
                    episodeDetailsNetworkDataSours.getAllEpisodes().body()?.episodes ?: emptyList()
                )
            )
        } catch (e: Throwable) {
            AnnaResponse.Failure(e)
        }
    }

    override suspend fun getEpisodeFromLocal(): AnnaResponse<List<SingleEpisodeListDomain>> {
        return try {
            val response = episodeLocalDao.getEpisodeListData()
            AnnaResponse.Success(
                mapperFromDataToDomain.map(response)
            )
        } catch (e: Throwable) {
            AnnaResponse.Failure(e)
        }
    }
}
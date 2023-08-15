package com.example.rickandmorty.data.episodes.list

import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.data.local.list.episodes.EpisodeLocalDao
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodeNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDataToListSingleEpisodeDomainMapper,
    private val episodeLocalDao: EpisodeLocalDao
) : EpisodesRepository {

    override fun getAllEpisodes(): Flow<AnnaResponse<List<SingleEpisodeListDomain>>> {
        return flow {
            emit(
                try {
                    val response = episodeDetailsNetworkDataSours.getAllEpisodes()
                    episodeLocalDao.setEpisodeListData(response.body()?.episodes ?: emptyList())
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            response.body()?.episodes
                                ?: emptyList()
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }

    }

    override fun getEpisodeFromLocal(): Flow<List<SingleEpisodeListDomain>> {
        return flow {
            emit(
                kotlin.runCatching {
                    val response = episodeLocalDao.getEpisodeListData()
                    mapperFromDataToDomain.map(response)
                }.getOrDefault(emptyList())
            )
        }
    }
}
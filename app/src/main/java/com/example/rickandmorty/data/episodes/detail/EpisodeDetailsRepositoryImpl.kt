package com.example.rickandmorty.data.episodes.detail

import com.example.rickandmorty.data.episodes.detail.api.EpisodesDetailsNetworkDataSource
import com.example.rickandmorty.data.episodes.detail.mapper.EpisodeDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.local.detail.episode.EpisodeDetailDao
import com.example.rickandmorty.domain.episode.details.EpisodeDetailRepository
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.tryMapSuspended
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodeDetailsRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodesDetailsNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDetailDataToEpisodeDetailDomainMapper,
    private val episodeDetailDao: EpisodeDetailDao
) : EpisodeDetailRepository {

    override fun loadEpisodeById(id: Int): Flow<AnnaResponse<EpisodeDetailsDomain>> {
        return flow {
            emit(
                try {
                    val response = episodeDetailsNetworkDataSours.getEpisodeDetails(id)
                    episodeDetailDao.deleteSingleEpisodeDetail()

                    episodeDetailDao.setSingleEpisodeDetail(response.body()!!)
                    AnnaResponse.Success(mapperFromDataToDomain.map(response.body()!!))
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }

    override fun loadEpisodeByIdLocal(id: Int): Flow<AnnaResponse<EpisodeDetailsDomain>> {
        return flow {
            emit(
                try {
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            episodeDetailDao.getSingleEpisodeDetail(id)
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }
}
package com.example.rickandmorty.data.episodes.detail

import com.example.rickandmorty.data.episodes.detail.api.EpisodesDetailsNetworkDataSource
import com.example.rickandmorty.data.episodes.detail.mapper.EpisodeDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.domain.episode.details.EpisodeDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.episode.details.EpisodeDetailRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeDetailsRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodesDetailsNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDetailDataToEpisodeDetailDomainMapper
) : EpisodeDetailRepository {

    private var callbackFromDataToDomainCallback: EpisodeDetailFromDataToDomainCallback? = null

    override fun registerFromDataToDomainCallback(callback: EpisodeDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadEpisodeById(int: Int) {
        episodeDetailsNetworkDataSours.getEpisodeDetails(int)
            .enqueue(object : Callback<SingleEpisodeDetailData> {
                override fun onResponse(
                    call: Call<SingleEpisodeDetailData>,
                    response: Response<SingleEpisodeDetailData>
                ) {
                    response.body()?.let {
                        callbackFromDataToDomainCallback?.getEpisodeDetail(
                            mapperFromDataToDomain.map(it)
                        )
                    }
                }

                override fun onFailure(call: Call<SingleEpisodeDetailData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
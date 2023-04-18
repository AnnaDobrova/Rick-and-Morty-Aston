package com.example.rickandmorty.data.episodes.detail

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.episodes.detail.api.EpisodesDetailsNetworkDataSource
import com.example.rickandmorty.data.episodes.detail.mapper.EpisodeDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.domain.episode.details.EpisodeDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailsRepositoryImpl : EpisodeDetailUseCase {

    private var episodeDetailsNetworkDataSours: EpisodesDetailsNetworkDataSource? = null
    private var callbackFromDataToDomainCallback: EpisodeDetailFromDataToDomainCallback? = null

    private val mapperFromDataToDomain by lazy {
        EpisodeDetailDataToEpisodeDetailDomainMapper()
    }

    init {
        episodeDetailsNetworkDataSours =
            RetrofitClient.fillRetrofit().create(EpisodesDetailsNetworkDataSource::class.java)
    }

    override fun registerFromDataToDomainCallback(callback: EpisodeDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadEpisodeById(id: Int) {
        episodeDetailsNetworkDataSours?.getEpisodeDetails(id)
            ?.enqueue(object : Callback<SingleEpisodeDetailData> {
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
package com.example.rickandmorty.data.episodes.list

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.domain.episode.list.EpisodeListFromDataToDomainCallBack
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFromDomainToUiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeRepositoryImpl : EpisodesRepository {
    private var episodeDetailsNetworkDataSours: EpisodeNetworkDataSource? = null
    private var callbackFromDataToDomainCallback: EpisodeListFromDataToDomainCallBack? = null

    private val mapperFromDataToDomain by lazy {
        EpisodeDataToListSingleEpisodeDomainMapper()
    }

    init {
        episodeDetailsNetworkDataSours =
            RetrofitClient.fillRetrofit().create(EpisodeNetworkDataSource::class.java)
    }

    override fun resisterFromDataToDomainCallback(callback: EpisodeListFromDataToDomainCallBack) {
        this.callbackFromDataToDomainCallback = callback
    }

    override fun loadAllEpisodes() {
        episodeDetailsNetworkDataSours?.getAllEpisodes()?.enqueue(object : Callback<EpisodeListData> {
            override fun onResponse(call: Call<EpisodeListData>, response: Response<EpisodeListData>) {

                callbackFromDataToDomainCallback?.getAllEpisodesFromDataToDomain(
                    mapperFromDataToDomain.map(
                        response.body()?.episodes ?: emptyList()
                    )
                )
            }

            override fun onFailure(call: Call<EpisodeListData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
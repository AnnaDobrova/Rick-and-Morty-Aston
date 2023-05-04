package com.example.rickandmorty.data.episodes.list

import com.example.rickandmorty.data.episodes.list.api.EpisodeNetworkDataSource
import com.example.rickandmorty.data.episodes.list.mapper.EpisodeDataToListSingleEpisodeDomainMapper
import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.domain.episode.list.EpisodeListFromDataToDomainCallBack
import com.example.rickandmorty.domain.episode.list.EpisodesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private var episodeDetailsNetworkDataSours: EpisodeNetworkDataSource,
    private val mapperFromDataToDomain: EpisodeDataToListSingleEpisodeDomainMapper
): EpisodesRepository {

    private var callbackFromDataToDomainCallback: EpisodeListFromDataToDomainCallBack? = null


    override fun resisterFromDataToDomainCallback(callback: EpisodeListFromDataToDomainCallBack) {
        this.callbackFromDataToDomainCallback = callback
    }

    override fun loadAllEpisodes() {
        episodeDetailsNetworkDataSours.getAllEpisodes().enqueue(object : Callback<EpisodeListData> {
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
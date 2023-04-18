package com.example.rickandmorty.data.episodes.detail.api

import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesDetailsNetworkDataSource {

    @GET("episode/{id}")
    fun getEpisodeDetails(@Path("id") episodeID: Int): Call<SingleEpisodeDetailData>
}
package com.example.rickandmorty.data.episodes.detail.api

import com.example.rickandmorty.data.episodes.detail.model.EpisodeDetailData
import com.example.rickandmorty.data.episodes.detail.model.SingleEpisodeDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesDetailsNetworkDataSource {

    @GET("episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") episodeID: Int): Response<SingleEpisodeDetailData>
}
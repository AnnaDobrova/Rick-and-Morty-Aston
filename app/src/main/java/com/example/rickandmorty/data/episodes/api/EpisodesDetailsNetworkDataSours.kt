package com.example.rickandmorty.data.episodes.api

import com.example.rickandmorty.data.episodes.model.EpisodesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesDetailsNetworkDataSours {

    @GET("episode/{id}")
    fun getEpisodeDetails(@Path("id") episodeID: Int): Call<EpisodesData.SingleEpisodeData>
}
package com.example.rickandmorty.data.episodes.api

import com.example.rickandmorty.data.episodes.model.EpisodesData
import retrofit2.Call
import retrofit2.http.GET

interface EpisodeNetworkDataSours {

    @GET("episode")
    fun getAllEpisodes(): Call<EpisodesData>
}
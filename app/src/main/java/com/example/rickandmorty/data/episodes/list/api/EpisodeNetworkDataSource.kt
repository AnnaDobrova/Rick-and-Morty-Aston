package com.example.rickandmorty.data.episodes.list.api

import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import retrofit2.Call
import retrofit2.http.GET

interface EpisodeNetworkDataSource {

    @GET("episode")
    fun getAllEpisodes(): Call<EpisodeListData>
}
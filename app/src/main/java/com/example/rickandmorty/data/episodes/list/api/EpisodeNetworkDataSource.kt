package com.example.rickandmorty.data.episodes.list.api

import com.example.rickandmorty.data.episodes.list.model.EpisodeListData
import com.example.rickandmorty.domain.episode.list.model.SingleEpisodeListDomain
import retrofit2.Response
import retrofit2.http.GET

interface EpisodeNetworkDataSource {

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodeListData>
}
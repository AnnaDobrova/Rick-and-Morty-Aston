package com.example.rickandmorty.data.episodes

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.episodes.api.EpisodeNetworkDataSours
import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.presentation.episodes.EpisodesListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesRepository {

    private var episodeNetworkDataSource: EpisodeNetworkDataSours? = null
    private var listener: EpisodesListener? = null

    init {
        episodeNetworkDataSource = RetrofitClient.fillRetrofit().create(EpisodeNetworkDataSours::class.java)
    }

    fun registerEpisode(listener: EpisodesListener) {
        this.listener = listener
    }

    fun loadEpisodes() {
        episodeNetworkDataSource?.getAllEpisodes()?.enqueue(object : Callback<EpisodesData> {
            override fun onResponse(call: Call<EpisodesData>, response: Response<EpisodesData>) {
                listener?.getAllEpisodes(response.body()?.episodes ?: emptyList())
            }

            override fun onFailure(call: Call<EpisodesData>, t: Throwable) {
                listener?.getAllEpisodes(emptyList())
            }
        })
    }
}
package com.example.rickandmorty.data.episodes

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.episodes.api.EpisodesDetailsNetworkDataSours
import com.example.rickandmorty.data.episodes.model.EpisodesData
import com.example.rickandmorty.presentation.episodes.EpisodeDetailsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailsRepository {

    private var episodeDetailsNetworkDataSours: EpisodesDetailsNetworkDataSours? = null
    private var listener: EpisodeDetailsListener? = null

    init {
        episodeDetailsNetworkDataSours =
            RetrofitClient.fillRetrofit().create(EpisodesDetailsNetworkDataSours::class.java)
    }

    fun registerListener(listener: EpisodeDetailsListener) {
        this.listener = listener
    }

    fun loadEpisodeById(id: Int) {
        episodeDetailsNetworkDataSours?.getEpisodeDetails(id)
            ?.enqueue(object  : Callback<EpisodesData.SingleEpisodeData>{
                override fun onResponse(
                    call: Call<EpisodesData.SingleEpisodeData>,
                    response: Response<EpisodesData.SingleEpisodeData>
                ) {
                    response.body()?.let { listener?.getEpisodeById(it) }
                }

                override fun onFailure(call: Call<EpisodesData.SingleEpisodeData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
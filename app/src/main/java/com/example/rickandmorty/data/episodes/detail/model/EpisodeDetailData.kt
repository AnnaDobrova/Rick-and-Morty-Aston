package com.example.rickandmorty.data.episodes.detail.model

import com.example.rickandmorty.data.characters.list.model.SingleCharacterInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeDetailData (
    @Expose
    val info: SingleCharacterInfoData,

    @SerializedName("results")
    @Expose
    val episodes: List<SingleEpisodeDetailData>
    )
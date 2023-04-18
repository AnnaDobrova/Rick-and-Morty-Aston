package com.example.rickandmorty.data.episodes.list.model

import com.example.rickandmorty.data.characters.list.model.SingleCharacterInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeListData(
    @Expose
    val info: SingleCharacterInfoData,

    @SerializedName("results")
    @Expose
    val episodes: List<SingleEpisodeListData>
) {
}
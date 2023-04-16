package com.example.rickandmorty.data.episodes.model

import com.example.rickandmorty.data.characters.list.model.CharactersData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodesData(
    @Expose
    val info: SingleCharacterInfoData,

    @SerializedName("results")
    @Expose
    val episodes: List<SingleEpisodeData>
) {
    data class SingleEpisodeData(
        @Expose
        val id: Int,
        @Expose
        val name: String,
        @Expose
        val air_date: String,
        @Expose
        val episode: String,
        @Expose
        val characters: List<String>,
        @Expose
        val url: String,
        @Expose
        val created: String
    )
}
package com.example.rickandmorty.data.characters.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Дата класс с данными основанными на json'е, который приходит по запросу characters
 */
data class CharactersData(
    @Expose
    val info: InfoData,

    @SerializedName("results")
    @Expose
    val characters: List<SingleCharacterData>
) {
    data class InfoData(
        @Expose
        val count: Long,
        @Expose
        val pages: Long,
        @Expose
        val next: String,
        @SerializedName("prev")
        @Expose
        val previous: String
    )

    data class SingleCharacterData(
        @Expose
        val id: Long,
        @Expose
        val name: String,
        @Expose
        val status: String,
        @Expose
        val species: String,
        @Expose
        val type: String,
        @Expose
        val gender: String,
        @Expose
        val origin: OriginData,
        @Expose
        val location: LocationData,
        @Expose
        val image: String,
        @SerializedName("episode")
        @Expose
        val episodes: List<String>,
        @Expose
        val url: String,
        @Expose
        val created: String
    ) {
        data class OriginData(
            @Expose
            val name: String,
            @Expose
            val url: String
        )

        data class LocationData(
            @Expose
            val name: String,
            @Expose
            val url: String
        )
    }
}
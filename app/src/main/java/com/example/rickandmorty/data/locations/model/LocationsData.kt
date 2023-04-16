package com.example.rickandmorty.data.locations.model

import com.example.rickandmorty.data.characters.list.model.CharactersData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationsData(
    @Expose
    val info: CharactersData.InfoData,

    @SerializedName("results")
    @Expose
    val locations: List<SingleLocationData>
) {

    data class SingleLocationData(
        @Expose
        val id: Long,
        @Expose
        val name: String,
        @Expose
        val type: String,
        @Expose
        val dimension: String,
        @SerializedName("residents")
        @Expose
        val listCharactersInLocation: List<String>,
        @Expose
        val url: String,
        @Expose
        val created: String
    )
}
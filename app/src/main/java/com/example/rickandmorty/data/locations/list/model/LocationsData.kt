package com.example.rickandmorty.data.locations.list.model

import com.example.rickandmorty.data.characters.list.model.SingleCharacterInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationsData(
    @Expose
    val info: SingleCharacterInfoData,

    @SerializedName("results")
    @Expose
    val locations: List<SingleLocationData>
)
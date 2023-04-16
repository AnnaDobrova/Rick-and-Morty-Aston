package com.example.rickandmorty.data.characters.list.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Дата класс с данными основанными на json'е, который приходит по запросу characters
 */
data class CharactersData(
    @Expose
    val info: SingleCharacterInfoData,

    @SerializedName("results")
    @Expose
    val characters: List<SingleCharacterData>
)
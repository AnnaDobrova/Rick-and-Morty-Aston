package com.example.rickandmorty.data.characters.api

import com.example.rickandmorty.data.characters.model.CharactersData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsNetworkDataSource {

    @GET("character/{id}")
    fun getCharacterDetails(@Path("id") characterId: Int): Call<CharactersData.CharacterDetailsData>

}

package com.example.rickandmorty.data.characters.detail.api

import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsNetworkDataSource {

    @GET("character/{id}")
    fun getCharacterDetails(@Path("id") characterId: Int): Call<CharacterDetailData>
}

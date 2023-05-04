package com.example.rickandmorty.data.characters.detail.api

import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsNetworkDataSource {

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): Response<CharacterDetailData>
}

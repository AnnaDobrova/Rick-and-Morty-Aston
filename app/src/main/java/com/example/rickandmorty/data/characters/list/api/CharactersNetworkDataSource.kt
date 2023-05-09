package com.example.rickandmorty.data.characters.list.api

import com.example.rickandmorty.data.characters.list.model.CharactersData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersNetworkDataSource {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int = 1): Response<CharactersData>
}
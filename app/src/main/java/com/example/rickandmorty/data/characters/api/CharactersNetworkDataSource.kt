package com.example.rickandmorty.data.characters.api

import com.example.rickandmorty.data.characters.model.CharactersData
import retrofit2.Call
import retrofit2.http.GET

interface CharactersNetworkDataSource {

    /**
     * C помощью ГЕТ(и др) мы даем понять Ретрофиту, что ему нужно будет взаимодействовать с этим методов
     * В обертку Call мы засовываем результат преобразования нашего json'а
     */
    @GET("character")
    fun getAllCharacters() : Call<CharactersData>
}
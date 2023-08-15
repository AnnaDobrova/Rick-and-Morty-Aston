package com.example.rickandmorty.data.characters.list.api

import com.example.rickandmorty.data.characters.list.model.CharactersData
import retrofit2.Response
import retrofit2.http.GET

interface CharactersNetworkDataSource {

    /**
     * C помощью ГЕТ(и др) мы даем понять Ретрофиту, что ему нужно будет взаимодействовать с этим методов
     * В обертку Call мы засовываем результат преобразования нашего json'а
     */
    @GET("character")
   suspend fun getAllCharacters(): Response<CharactersData>
}
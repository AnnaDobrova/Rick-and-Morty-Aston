package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.model.CharactersData
import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * На данный момент(до изучения архитектуры) этот класс отвечает за взаимодействие между нашим CharactersFragment и
 * Ретрофитом с его походом в сеть
 */
class CharactersRepositoryImpl : CharactersRepository {

    private var charactersNetworkDataSource: CharactersNetworkDataSource? = null
    private val mapperFromDataToDomain by lazy {
        CharactersDataToListSingleCharacterDomainMapper()
    }

    init {
        /**
         * Тут мы говорим Ретрофиту что ему нужно работать с этим интерфейсом и его методами
         */
        charactersNetworkDataSource = RetrofitClient.fillRetrofit().create(CharactersNetworkDataSource::class.java)
    }

    /**
     * Метод отвечает за получение данных о всех персонажах. Варианты ответа, onResponse - удачное получение данных
     * onFailure - какая то ошибка
     */
    override fun loadAllCharacters(): List<SingleCharacterDomain> {
        return charactersNetworkDataSource?.getAllCharacters()?.body()?.let {
            mapperFromDataToDomain.map(it)
        } ?: emptyList()
    }

}
package com.example.rickandmorty.data.characters

import com.example.rickandmorty.data.characters.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.presentation.characters.CharactersListener
import com.example.rickandmorty.data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * На данный момент(до изучения архитектуры) этот класс отвечает за взаимодействие между нашим CharactersFragment и
 * Ретрофитом с его походом в сеть
 */
class CharactersRepository {

    private var charactersNetworkDataSource: CharactersNetworkDataSource? = null
    private var listener: CharactersListener? = null

    init {
        /**
         * Тут мы говорим Ретрофиту что ему нужно работать с этим интерфейсом и его методами
         */
        charactersNetworkDataSource = RetrofitClient.fillRetrofit().create(CharactersNetworkDataSource::class.java)
    }

    /**
     * Метод который регистрирует слушателя, где во входящие параметры приходит то, где реализован этот CharactersListener,
     * в нашем случае его реализация в CharactersFragment, т.о во входящие параметры придет CharactersListener, с реализацией
     * в CharactersFragment
     */
    fun registerListener(listener: CharactersListener) {
        this.listener = listener
    }

    /**
     * Метод отвечает за получение данных о всех персонажах. Варианты ответа, onResponse - удачное получение данных
     * onFailure - какая то ошибка
     */
    fun loadAllCharacters() {
        charactersNetworkDataSource?.getAllCharacters()?.enqueue(object : Callback<CharactersData> {
            override fun onResponse(call: Call<CharactersData>, response: Response<CharactersData>) {
                listener?.getAllCharacters(response.body()?.characters ?: emptyList())
            }

            override fun onFailure(call: Call<CharactersData>, t: Throwable) {
                listener?.getAllCharacters(emptyList())
            }
        })
    }
}
package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.characters.list.model.CharactersData
import com.example.rickandmorty.domain.character.list.CharacterListFromDataToDomainCallback
import com.example.rickandmorty.domain.character.list.CharactersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * На данный момент(до изучения архитектуры) этот класс отвечает за взаимодействие между нашим CharactersFragment и
 * Ретрофитом с его походом в сеть
 */
class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper
) : CharactersRepository {

    private var callbackFromDataToDomain: CharacterListFromDataToDomainCallback? = null

    /**
     * 3 шаг
     * реалиация регистрации коллбэка
     */
    override fun registerFromDataToDomainCallback(callback: CharacterListFromDataToDomainCallback) {
        this.callbackFromDataToDomain = callback
    }

    /**
     *  7 шаг
     *  Метод отвечает за получение данных о всех персонажах. Варианты ответа, onResponse - удачное получение данных
     * onFailure - какая то ошибка
     */

    override fun loadAllCharacters() {
        charactersNetworkDataSource.getAllCharacters()?.enqueue(object : Callback<CharactersData> {
            override fun onResponse(call: Call<CharactersData>, response: Response<CharactersData>) {
                /**
                 * 9 шаг, данные смаппились и мы их приняли в наш коллбэк
                 */
                callbackFromDataToDomain?.getAllCharactersFromDataToDomain(
                    mapperFromDataToDomain.map(
                        response.body()?.characters ?: emptyList()
                    )
                )
            }

            override fun onFailure(call: Call<CharactersData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
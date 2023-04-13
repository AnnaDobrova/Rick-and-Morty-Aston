package com.example.rickandmorty.data.characters

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.characters.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.presentation.characters.CharacterDetailsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsRepository {

    private var characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource? = null
    private var listener: CharacterDetailsListener? = null

    init {
        characterDetailsNetworkDataSource =
            RetrofitClient.fillRetrofit().create(CharacterDetailsNetworkDataSource::class.java)
    }

    fun registerListener(listener: CharacterDetailsListener) {
        this.listener = listener
    }

    fun loadCharacterById(id: Int) {
        characterDetailsNetworkDataSource?.getCharacterDetails(id)
            ?.enqueue(object : Callback<CharactersData.CharacterDetailsData> {
                override fun onResponse(
                    call: Call<CharactersData.CharacterDetailsData>,
                    response: Response<CharactersData.CharacterDetailsData>
                ) {
                    response.body()?.let { listener?.getCharacterById(it) }
                }

                override fun onFailure(call: Call<CharactersData.CharacterDetailsData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
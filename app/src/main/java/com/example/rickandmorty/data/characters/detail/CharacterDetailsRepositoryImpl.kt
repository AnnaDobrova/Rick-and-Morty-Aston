package com.example.rickandmorty.data.characters.detail

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsRepositoryImpl : CharacterDetailRepository {

    private var characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource? = null
    private var listener: CharacterDetailsListener? = null

    init {
        characterDetailsNetworkDataSource =
            RetrofitClient.fillRetrofit().create(CharacterDetailsNetworkDataSource::class.java)
    }

    override fun loadCharacterById(id: Int) {
        characterDetailsNetworkDataSource?.getCharacterDetails(id)
            ?.enqueue(object : Callback<SingleCharacterData> {
                override fun onResponse(
                    call: Call<SingleCharacterData>,
                    response: Response<SingleCharacterData>
                ) {
                    response.body()?.let { listener?.getCharacterById(it) }
                }

                override fun onFailure(call: Call<SingleCharacterData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun registerListener(listener: CharacterDetailsListener) {
        this.listener = listener
    }
}
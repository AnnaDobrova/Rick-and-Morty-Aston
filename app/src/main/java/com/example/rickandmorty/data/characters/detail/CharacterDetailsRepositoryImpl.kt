package com.example.rickandmorty.data.characters.detail

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.detail.mapper.CharacterDetailDataToCharacterDetailDomainMapper
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterData
import com.example.rickandmorty.domain.character.detail.CharacterDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import com.example.rickandmorty.domain.character.list.CharacterListFromDataToDomainCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsRepositoryImpl : CharacterDetailRepository {

    private var characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource? = null

    private var callbackFromDataToDomainCallback: CharacterDetailFromDataToDomainCallback? = null

    private val mapperFromDataToDomain by lazy {
        CharacterDetailDataToCharacterDetailDomainMapper()
    }

    init {
        characterDetailsNetworkDataSource =
            RetrofitClient.fillRetrofit().create(CharacterDetailsNetworkDataSource::class.java)
    }

    override fun registerFromDataToDomainCallback(callback: CharacterDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadCharacterById(id: Int) {
        characterDetailsNetworkDataSource?.getCharacterDetails(id)
            ?.enqueue(object : Callback<CharacterDetailData> {
                override fun onResponse(
                    call: Call<CharacterDetailData>,
                    response: Response<CharacterDetailData>
                ) {
                    response.body()?.let {
                        callbackFromDataToDomainCallback?.getCharacterById(
                            mapperFromDataToDomain.map(it)
                        )
                    }
                }

                override fun onFailure(call: Call<CharacterDetailData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

}
package com.example.rickandmorty.data.characters.detail

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.detail.mapper.CharacterDetailDataToCharacterDetailDomainMapper
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailData
import com.example.rickandmorty.domain.character.detail.CharacterDetailFromDataToDomainCallback
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource,
    private val mapperFromDataToDomain: CharacterDetailDataToCharacterDetailDomainMapper
) : CharacterDetailRepository {

    private var callbackFromDataToDomainCallback: CharacterDetailFromDataToDomainCallback? = null

    override fun registerFromDataToDomainCallback(callback: CharacterDetailFromDataToDomainCallback) {
        callbackFromDataToDomainCallback = callback
    }

    override fun loadCharacterById(id: Int) {
        characterDetailsNetworkDataSource.getCharacterDetails(id)
            .enqueue(object : Callback<CharacterDetailData> {
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
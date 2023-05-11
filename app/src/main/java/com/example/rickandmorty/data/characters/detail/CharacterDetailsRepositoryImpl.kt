package com.example.rickandmorty.data.characters.detail

import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.detail.mapper.CharacterDetailDataToCharacterDetailDomainMapper
import com.example.rickandmorty.data.local.detail.character.CharacterDetailDao
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.tryMapSuspended
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource,
    private val mapperFromDataToDomain: CharacterDetailDataToCharacterDetailDomainMapper,
    private val characterDetailDao: CharacterDetailDao
) : CharacterDetailRepository {

    override fun loadCharacterById(id: Int): Flow<AnnaResponse<CharacterDetailDomain>> {
        return flow {
            emit(
                try {
                    val response = characterDetailsNetworkDataSource.getCharacterDetails(id)
                    characterDetailDao.deleteCharacterDetail()

                    characterDetailDao.setCharacterDetail(response.body()!!)
                    AnnaResponse.Success(mapperFromDataToDomain.map(response.body()!!))
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }

    }

    override fun loadCharacterByIdFromLocal(id: Int): Flow<AnnaResponse<CharacterDetailDomain>> {
        return flow {
            emit(
                try {
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            characterDetailDao.getCharacterDetail(id)
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }
}
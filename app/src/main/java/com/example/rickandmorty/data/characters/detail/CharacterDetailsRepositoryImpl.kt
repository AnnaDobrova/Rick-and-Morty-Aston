package com.example.rickandmorty.data.characters.detail

import com.example.rickandmorty.data.characters.detail.api.CharacterDetailsNetworkDataSource
import com.example.rickandmorty.data.characters.detail.mapper.CharacterDetailDataToCharacterDetailDomainMapper
import com.example.rickandmorty.domain.character.detail.CharacterDetailRepository
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.tryMapSuspended
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val characterDetailsNetworkDataSource: CharacterDetailsNetworkDataSource,
    private val mapperFromDataToDomain: CharacterDetailDataToCharacterDetailDomainMapper
) : CharacterDetailRepository {

    override fun loadCharacterById(id: Int): Flow<AnnaResponse<CharacterDetailDomain>> {
        return flow {
            emit(
                tryMapSuspended(
                    request = { characterDetailsNetworkDataSource.getCharacterDetails(id) },
                    mapSuccess = { AnnaResponse.Success(mapperFromDataToDomain.map(it)) },
                    mapFailure = { AnnaResponse.Failure(Throwable(it.message())) }
                )
            )
        }
    }
}
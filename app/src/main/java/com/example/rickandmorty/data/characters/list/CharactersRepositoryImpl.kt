package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.local.list.characters.CharacterLocalDao
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper,
    private val characterLocalDao: CharacterLocalDao,
) : CharactersRepository {

    override fun getAllCharacters(): Flow<AnnaResponse<List<SingleCharacterDomain>>> {
        return flow {
            emit(
                try {
                    val response = charactersNetworkDataSource.getAllCharacters()
                    characterLocalDao.setCharacterList(response.body()?.characters ?: emptyList())
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            response.body()?.characters
                                ?: emptyList()
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )

        }
    }

    override fun getAllCharactersFromLocal(): Flow<List<SingleCharacterDomain>> {
        return flow {
            emit(
                kotlin.runCatching {
                    val response = characterLocalDao.getCharacterList()
                    mapperFromDataToDomain.map(response)
                }.getOrDefault(emptyList())
            )
        }
    }
}
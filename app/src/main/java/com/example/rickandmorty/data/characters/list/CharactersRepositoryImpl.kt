package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.local.list.characters.CharacterLocalDao
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper,
    private val characterLocalDao: CharacterLocalDao,
) : CharactersRepository {

    override suspend fun getAllCharacters(): AnnaResponse<List<SingleCharacterDomain>> {
        return try {
            val response = charactersNetworkDataSource.getAllCharacters()
            characterLocalDao.deleteCharacterList()
            characterLocalDao.setCharacterList(response.body()?.characters ?: emptyList())
            AnnaResponse.Success(
                mapperFromDataToDomain.map(
                    response.body()?.characters ?: emptyList()
                )
            )
        } catch (e: Throwable) {
            AnnaResponse.Failure(e)
        }
    }

    override suspend fun getAllCharactersFromLocal(): AnnaResponse<List<SingleCharacterDomain>> {
        return try {
            val response = characterLocalDao.getCharacterList()
            AnnaResponse.Success(
                mapperFromDataToDomain.map(response)
            )
        } catch (e: Throwable) {
            AnnaResponse.Failure(e)
        }
    }
}
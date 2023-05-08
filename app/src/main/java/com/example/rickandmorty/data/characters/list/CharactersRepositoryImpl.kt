package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper
) : CharactersRepository {

    override suspend fun getAllCharacters(): AnnaResponse<List<SingleCharacterDomain>> {
        return try {
            AnnaResponse.Success(
                mapperFromDataToDomain.map(
                    charactersNetworkDataSource.getAllCharacters().body()?.characters ?: emptyList()
                )
            )
        } catch (e: Throwable) {
            AnnaResponse.Failure(e)
        }
    }
}
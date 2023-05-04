package com.example.rickandmorty.data.characters.list

import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper
) : CharactersRepository {

    override suspend fun getAllCharacters(): List<SingleCharacterDomain> {
        return mapperFromDataToDomain.map(
            charactersNetworkDataSource.getAllCharacters().body()?.characters ?: emptyList()
        )
    }
}
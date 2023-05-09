package com.example.rickandmorty.data.characters.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.api.paging.CharactersPagingSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.domain.character.list.CharactersRepository
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersNetworkDataSource: CharactersNetworkDataSource,
    private val characterLocalDao: CharacterLocalDao,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper,
) : CharactersRepository {

    override fun getAllCharacters(): Flow<PagingData<SingleCharacterDomain>> {
        val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)
        return Pager(pagingConfig) {
            CharactersPagingSource(charactersNetworkDataSource, characterLocalDao, mapperFromDataToDomain)
        }.flow.flowOn(Dispatchers.IO)
    }

    override fun getAllCharactersFromLocal(): Flow<PagingData<SingleCharacterDomain>> {
        val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

        return Pager(pagingConfig) {
            characterLocalDao.getCharacterList()
        }.flow.flowOn(Dispatchers.IO).map {
            mapperFromDataToDomain.mapFromLocal(it)
        }
    }
}
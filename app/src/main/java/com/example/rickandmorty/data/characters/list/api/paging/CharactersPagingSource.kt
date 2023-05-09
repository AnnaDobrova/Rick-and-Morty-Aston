package com.example.rickandmorty.data.characters.list.api.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.characters.list.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.characters.list.mapper.CharactersDataToListSingleCharacterDomainMapper
import com.example.rickandmorty.data.local.characters.CharacterLocalDao
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain

class CharactersPagingSource(
    private val charactersService: CharactersNetworkDataSource,
    private val charactersDao: CharacterLocalDao,
    private val mapperFromDataToDomain: CharactersDataToListSingleCharacterDomainMapper,
) : PagingSource<Int, SingleCharacterDomain>() {

    private lateinit var responseData: List<SingleCharacterDomain>

    override fun getRefreshKey(state: PagingState<Int, SingleCharacterDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleCharacterDomain> {
        return try {
            val currentPage = params.key ?: 1
            val response = charactersService.getAllCharacters(currentPage).body()
            responseData = mapperFromDataToDomain.mapFromRemote(response?.characters ?: emptyList())
            if (response?.characters != null && response.characters.isNotEmpty()) {
                charactersDao.deleteCharacterList()
                charactersDao.setCharacterList(response.characters)
            }
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (responseData.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}
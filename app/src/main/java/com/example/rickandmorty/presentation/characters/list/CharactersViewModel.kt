package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.utils.network.NetworkState
import com.example.rickandmorty.utils.network.NetworkStateTracker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val mapperFromDomainToUi: SingleCharacterDomainToSingleCharacterUiMapper,
    private val networkStateTracker: NetworkStateTracker
) : ViewModel() {

    private val _networkState = networkStateTracker.networkStatus.flowOn(Dispatchers.IO)

    private var characters: Flow<PagingData<SingleCharacterDomain>>? = null

    init {
        initAllCharacters()
    }

    fun initAllCharacters() {
        viewModelScope.launch {
            _networkState.collect { status ->
                when (status) {
                    is NetworkState.Available -> loadAllCharacters()
                    is NetworkState.Unavailable -> loadAllCharactersFromLocal()
                }
            }
        }
    }

    fun getAllCharacters(): Flow<PagingData<SingleCharacterUi>>? = characters?.map {
        mapperFromDomainToUi.map(it)
    }

    fun getNetworkState(): Flow<NetworkState> = _networkState

    private fun loadAllCharacters() {
        characters = charactersUseCase.getAllCharacters().cachedIn(viewModelScope)
    }

    private fun loadAllCharactersFromLocal() {
        characters = charactersUseCase.getAllCharactersFromLocal().cachedIn(viewModelScope)
    }
}
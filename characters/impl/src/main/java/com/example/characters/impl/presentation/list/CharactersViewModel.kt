package com.example.characters.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_ui.AnnaResponse
import com.example.core_ui.Connectivity
import com.example.core_ui.ViewState
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.Connectivity
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val mapperFromDomainToUi: SingleCharacterDomainToSingleCharacterUiMapper,
    private val connectivity: Connectivity
) : ViewModel() {

    private var characters = MutableStateFlow<ViewState<List<SingleCharacterUi>>>(ViewState.Loading)

    init {
        loadCharacters()
    }

    private fun loadAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersUseCase.getAllCharacters().collect { annaResponse ->
                characters.value = when (annaResponse) {
                    is AnnaResponse.Success -> ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))
                    is AnnaResponse.Failure -> ViewState.Error(annaResponse.error)
                }
            }
        }
    }

    private fun loadAllCharactersFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersUseCase.getAllCharactersFromLocal().collect { data ->
                characters.value = ViewState.Data(mapperFromDomainToUi.map(data))
            }
        }
    }

    fun loadCharacters() {
        if (connectivity.isNetworkAvailable()) {
            loadAllCharacters()
        } else {
            characters.value = ViewState.Error(Throwable("Отсутствует интернет соединение"))
            loadAllCharactersFromLocal()
        }
    }

    fun getAllCharacters(): MutableStateFlow<ViewState<List<SingleCharacterUi>>> = characters
}
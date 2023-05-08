package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val mapperFromDomainToUi: SingleCharacterDomainToSingleCharacterUiMapper
) : ViewModel() {

    private var characters = MutableLiveData<List<SingleCharacterUi>>(emptyList())

    private val error = MutableLiveData<String>()

    init {
        loadAllCharacters()
    }

    fun loadAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = charactersUseCase.getAllCharacters()) {
                is AnnaResponse.Success -> characters.postValue(mapperFromDomainToUi.map(response.data))
                is AnnaResponse.Failure -> {
                    error.postValue(response.error.message)
                }
            }

        }
    }

    fun getAllCharacters(): LiveData<List<SingleCharacterUi>> = characters
    fun getError(): LiveData<String> = error
}
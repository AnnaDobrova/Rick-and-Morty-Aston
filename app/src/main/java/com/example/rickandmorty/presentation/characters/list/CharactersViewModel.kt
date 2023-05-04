package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val mapperFromDomainToUi: SingleCharacterDomainToSingleCharacterUiMapper
) : ViewModel() {

    private var characters = MutableLiveData<List<SingleCharacterUi>>(emptyList())

    init {
        loadAllCharacters()
    }

    fun loadAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            characters.postValue(mapperFromDomainToUi.map(charactersUseCase.getAllCharacters()))
        }
    }

    fun getAllCharacters(): LiveData<List<SingleCharacterUi>> = characters

}
package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.CharactersUseCaseImpl
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

class CharactersViewModel : ViewModel() {

    private var _characters = MutableLiveData<List<SingleCharacterUi>>(emptyList())

    private var charactersUseCase: CharactersUseCase? = null
    private val mapperFromDomainToUi by lazy {
        SingleCharacterDomainToSingleCharacterUiMapper()
    }

    fun getAllCharacters(): LiveData<List<SingleCharacterUi>> = _characters

    init {
        charactersUseCase = CharactersUseCaseImpl()
        loadAllCharacters()
    }

    private fun loadAllCharacters() {
        _characters.postValue(
            charactersUseCase?.loadAllCharacters()?.let {
                mapperFromDomainToUi.map(
                    it
                )
            }
        )
    }
}
package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.CharactersUseCaseImpl
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

class CharactersViewModel : ViewModel(), CharacterListFromDomainToUiCallback {

    private var characters = MutableLiveData<List<SingleCharacterUi>>(emptyList())

    private var charactersUseCase: CharactersUseCase? = null
    private val mapperFromDomainToUi by lazy {
        SingleCharacterDomainToSingleCharacterUiMapper()
    }

    fun getAllCharacters(): LiveData<List<SingleCharacterUi>> = characters

    init {
        charactersUseCase = CharactersUseCaseImpl()
        charactersUseCase?.registerFromDomainToUiCallback(this@CharactersViewModel)
        loadAllCharacters()
    }

    private fun loadAllCharacters() {
        charactersUseCase?.loadAllCharacters()
    }

    override fun getAllCharacters(characterList: List<SingleCharacterDomain>) {
        characters.postValue(
            mapperFromDomainToUi.map(characterList)
        )
    }
}
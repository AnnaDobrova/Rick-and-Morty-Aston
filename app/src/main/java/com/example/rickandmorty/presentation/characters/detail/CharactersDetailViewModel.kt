package com.example.rickandmorty.presentation.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import javax.inject.Inject

class CharactersDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val mapperFromDomainToUi: CharacterDetailDomainToCharacterDetailUiMapper
) : ViewModel(), CharacterDetailFromDomainToUiCallback {

    private var character = MutableLiveData<CharacterDetailUi>()

    init {
        characterDetailUseCase.registerFromDomainToUiCallback(this@CharactersDetailViewModel)
    }

    override fun getCharacterDetail(characterDetail: CharacterDetailDomain) {
        character.postValue(mapperFromDomainToUi.map(characterDetail))
    }

    fun loadCharacterById(id: Int) {
        characterDetailUseCase.loadCharacterById(id)
    }

    fun getCharacter(): LiveData<CharacterDetailUi> = character
}
package com.example.rickandmorty.presentation.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val mapperFromDomainToUi: CharacterDetailDomainToCharacterDetailUiMapper
) : ViewModel() {

    private var character = MutableLiveData<CharacterDetailUi>()

    fun loadCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDetailUseCase.loadCharacterById(id).collect { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        character.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        Throwable(annaResponse.error)
                    }
                }
            }
        }
    }

    fun getCharacter(): LiveData<CharacterDetailUi> = character
}
package com.example.rickandmorty.presentation.locations.detail

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private var locationDetailUseCase: LocationDetailUseCase,
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val mapperFromDomainToUi: LocationDetailsDomainToLocationDetailsUIMapper,
    private val mapperFromDomainToUiForCharacters: CharacterDetailDomainToCharacterDetailUiMapper,
) : ViewModel() {

    private var locations = MutableLiveData<LocationDetailUi>()
    private var characterList = MutableLiveData<List<CharacterDetailUi>>(emptyList())

    fun loadLocationById(id: Int) {
        viewModelScope.launch {
            locationDetailUseCase.loadLocationById(id).collect() { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        locations.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        Throwable(annaResponse.error)
                        loadLocationByIdFromLocal(id)
                    }
                }
            }
        }
    }

    private fun loadLocationByIdFromLocal(id: Int) {
        viewModelScope.launch {
            locationDetailUseCase.loadLocationByIdFromLocal(id).collect() { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        locations.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        Throwable(annaResponse.error)
                    }
                }
            }
        }
    }

    fun getLocation(): LiveData<LocationDetailUi> = locations

    fun getCharacterList(): LiveData<List<CharacterDetailUi>> = characterList

    fun loadCharacterById(characterStringList: List<String>) {
        viewModelScope.launch {
            val idsList = mutableListOf<Int>()
            val list = mutableListOf<CharacterDetailUi>()
            launch {
                characterStringList.map { character ->
                    if (character.checkLastLetters() != 0) idsList.add(character.checkLastLetters())
                }
            }.join()

            launch {
                idsList.map { id ->
                    characterDetailUseCase.loadCharacterById(id).collect { annaResponse ->
                        when (annaResponse) {
                            is AnnaResponse.Success -> {
                                list.add(mapperFromDomainToUiForCharacters.map(annaResponse.data))
                            }

                            is AnnaResponse.Failure -> {
                                Throwable(annaResponse.error)
                            }
                        }
                    }
                }
            }.join()
            characterList.postValue(list)
        }
    }

    private fun String.checkLastLetters(): Int {
        val lastThree = takeLast(3)
        if (lastThree.isDigitsOnly().not()) {
            println("Last three letters are not digits.")
            val lastTwo = takeLast(2)
            if (lastTwo.isDigitsOnly().not()) {
                println("Last two letters are not digits.")
                val lastOne = takeLast(1)
                if (lastOne.isDigitsOnly().not()) {
                    println("Last letter is not a digit.")
                } else {
                    return lastOne.toInt()
                }
            } else {
                return lastTwo.toInt()
            }
        } else {
            return lastThree.toInt()
        }
        return 0
    }
}
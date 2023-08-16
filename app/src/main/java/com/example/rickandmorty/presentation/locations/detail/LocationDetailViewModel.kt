package com.example.rickandmorty.presentation.locations.detail

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.location.detail.LocationDetailUseCase
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.locations.detail.mapper.LocationDetailsDomainToLocationDetailsUIMapper
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.Connectivity
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private var locationDetailUseCase: LocationDetailUseCase,
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val mapperFromDomainToUi: LocationDetailsDomainToLocationDetailsUIMapper,
    private val mapperFromDomainToUiForCharacters: CharacterDetailDomainToCharacterDetailUiMapper,
    private val connectivity: Connectivity
) : ViewModel() {

    private var locations = MutableStateFlow<ViewState<LocationDetailUi>>(ViewState.Loading)
    private var characterList =
        MutableStateFlow<ViewState<List<CharacterDetailUi>>>(ViewState.Loading)

    private fun loadLocationById(id: Int) {
        viewModelScope.launch {
            locationDetailUseCase.loadLocationById(id).collect { annaResponse ->
                locations.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure -> ViewState.Error(annaResponse.error)
                }
            }
        }
    }

    private fun loadLocationByIdFromLocal(id: Int) {
        viewModelScope.launch {
            locationDetailUseCase.loadLocationByIdFromLocal(id).collect() { annaResponse ->
                locations.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure -> ViewState.Error(annaResponse.error)
                }
            }
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
            characterList.value = ViewState.Data(list)
        }
    }

    fun loadLocationDetail(id: Int) {
        if (connectivity.isNetworkAvailable()) {
            loadLocationById(id)
        } else {
            loadLocationByIdFromLocal(id)
            locations.value = ViewState.Error(Throwable("Отсутствует интернет соединение"))
        }
    }

    fun getLocation(): MutableStateFlow<ViewState<LocationDetailUi>> = locations

    fun getCharacterList(): MutableStateFlow<ViewState<List<CharacterDetailUi>>> = characterList
}
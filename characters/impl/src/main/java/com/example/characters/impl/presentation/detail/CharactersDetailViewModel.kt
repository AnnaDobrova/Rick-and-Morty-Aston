package com.example.characters.impl.presentation.detail

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_ui.AnnaResponse
import com.example.core_ui.Connectivity
import com.example.core_ui.ViewState
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.characters.impl.presentation.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val episodeDetailUseCase: EpisodeDetailUseCase,
    private val mapperFromDomainToUi: CharacterDetailDomainToCharacterDetailUiMapper,
    private val mapperFromDomainToUiForEpisodes: EpisodeDetailDomainToEpisodeDetailUiMapper,
    private val connectivity: Connectivity
) : ViewModel() {

    private var character = MutableStateFlow<ViewState<CharacterDetailUi>>(ViewState.Loading)

    private var episodeList = MutableStateFlow<ViewState<List<EpisodeDetailUi>>>(ViewState.Loading)


    private fun getCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDetailUseCase.loadCharacterById(id).collect { annaResponse ->
                character.value = when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure ->
                        ViewState.Error(annaResponse.error)
                }
            }
        }
    }

    private fun getCharacterByIdFromLocal(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterDetailUseCase.loadCharacterByIdFromLocal(id).collect { annaResponse ->
                character.value =   when (annaResponse) {
                    is AnnaResponse.Success ->
                        ViewState.Data(mapperFromDomainToUi.map(annaResponse.data))

                    is AnnaResponse.Failure ->
                       ViewState.Error(annaResponse.error)
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

    fun loadCharacterDetail(id: Int) {
        if (connectivity.isNetworkAvailable()) {
            getCharacterById(id)
        } else {
            getCharacterByIdFromLocal(id)
            character.value = ViewState.Error(Throwable("Отсутствует интернет соединение"))
        }
    }

    fun loadEpisodeById(episodeStringList: List<String>) {
        viewModelScope.launch {
            val idsList = mutableListOf<Int>()
            val list = mutableListOf<EpisodeDetailUi>()
            launch {
                episodeStringList.map { episode ->
                    if (episode.checkLastLetters() != 0) idsList.add(episode.checkLastLetters())
                }
            }.join()

            launch {
                idsList.map { id ->
                    episodeDetailUseCase.loadEpisodeById(id).collect { annaResponse ->
                        when (annaResponse) {
                            is AnnaResponse.Success -> {
                                list.add(mapperFromDomainToUiForEpisodes.map(annaResponse.data))

                            }

                            is AnnaResponse.Failure -> {
                                Throwable(annaResponse.error.message)
                            }
                        }
                    }
                }
            }.join()
            episodeList.value = ViewState.Data(list)
        }
    }

    fun getCharacter(): MutableStateFlow<ViewState<CharacterDetailUi>> = character

    fun getEpisodeList(): MutableStateFlow<ViewState<List<EpisodeDetailUi>>> = episodeList
}
package com.example.rickandmorty.presentation.characters.detail

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val episodeDetailUseCase: EpisodeDetailUseCase,
    private val mapperFromDomainToUi: CharacterDetailDomainToCharacterDetailUiMapper,
    private val mapperFromDomainToUiForEpisodes: EpisodeDetailDomainToEpisodeDetailUiMapper
) : ViewModel() {

    private var character = MutableLiveData<CharacterDetailUi>()

    private var episodeList = MutableLiveData<List<EpisodeDetailUi>>(emptyList())

    private var error = MutableLiveData<String>()

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            characterDetailUseCase.loadCharacterById(id).collect { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        character.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        error.postValue(Throwable(annaResponse.error).message)
                        getCharacterByIdFromLocal(id)
                    }
                }
            }
        }
    }

    private suspend fun getCharacterByIdFromLocal(id: Int) {
        characterDetailUseCase.loadCharacterByIdFromLocal(id).collect { annaResponse ->
            when (annaResponse) {
                is AnnaResponse.Success -> {
                    character.postValue(mapperFromDomainToUi.map(annaResponse.data))
                }

                is AnnaResponse.Failure -> {
                    error.postValue(Throwable(annaResponse.error).message)
                }
            }
        }
    }

    fun getCharacter(): LiveData<CharacterDetailUi> = character

    fun getEpisodeList(): LiveData<List<EpisodeDetailUi>> = episodeList

    fun getError(): LiveData<String> = error

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
                                Throwable(annaResponse.error)
                            }
                        }
                    }
                }
            }.join()
            episodeList.postValue(list)
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
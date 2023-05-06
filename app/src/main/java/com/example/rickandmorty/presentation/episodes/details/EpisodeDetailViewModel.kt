package com.example.rickandmorty.presentation.episodes.details

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.character.detail.CharacterDetailUseCase
import com.example.rickandmorty.domain.episode.details.EpisodeDetailUseCase
import com.example.rickandmorty.domain.episode.details.model.EpisodeDetailsDomain
import com.example.rickandmorty.presentation.characters.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.details.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private val episodeDetailUseCase: EpisodeDetailUseCase,
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val mapperFromDomainToUi: EpisodeDetailDomainToEpisodeDetailUiMapper,
    private val mapperFromDomainToUiForCharacters: CharacterDetailDomainToCharacterDetailUiMapper,
) : ViewModel() {

    private var episodes = MutableLiveData<EpisodeDetailUi>()
    private var characterList = MutableLiveData<List<CharacterDetailUi>>(emptyList())

    fun loadEpisodeById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            episodeDetailUseCase.loadEpisodeById(id).collect() { annaResponse ->
                when (annaResponse) {
                    is AnnaResponse.Success -> {
                        episodes.postValue(mapperFromDomainToUi.map(annaResponse.data))
                    }

                    is AnnaResponse.Failure -> {
                        Throwable(annaResponse.error)
                    }
                }
            }
        }
    }

    fun getEpisode(): LiveData<EpisodeDetailUi> = episodes

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

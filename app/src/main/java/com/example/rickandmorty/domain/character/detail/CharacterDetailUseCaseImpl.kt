package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.data.characters.detail.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.data.episodes.detail.EpisodeDetailsRepositoryImpl
import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailFromDomainToUiCallback

class CharacterDetailUseCaseImpl : CharacterDetailUseCase, CharacterDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: CharacterDetailFromDomainToUiCallback? = null
    private val characterDetailsRepository: CharacterDetailsRepositoryImpl by lazy {
        CharacterDetailsRepositoryImpl()
    }

    private val episodeDetailsRepositoryImpl: EpisodeDetailsRepositoryImpl by lazy {
        EpisodeDetailsRepositoryImpl()
    }

    init {
        characterDetailsRepository.registerFromDataToDomainCallback(this)
    }

    override fun registerFromDomainToUiCallback(callback: CharacterDetailFromDomainToUiCallback) {
        callbackFromDomainToUiCallback = callback
    }

    override fun loadCharacterById(id: Int) {
        characterDetailsRepository.loadCharacterById(id)
    }

    override fun getCharacterById(characterDetail: CharacterDetailDomain) {
        callbackFromDomainToUiCallback?.getCharacterDetail(characterDetail)
    }

}
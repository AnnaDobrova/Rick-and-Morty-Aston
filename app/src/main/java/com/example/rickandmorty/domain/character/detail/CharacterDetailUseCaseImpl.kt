package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailFromDomainToUiCallback
import javax.inject.Inject

class CharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailsRepository: CharacterDetailRepository
) : CharacterDetailUseCase, CharacterDetailFromDataToDomainCallback {

    private var callbackFromDomainToUiCallback: CharacterDetailFromDomainToUiCallback? = null

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
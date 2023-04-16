package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.data.characters.list.CharactersRepositoryImpl
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.CharacterListFromDomainToUiCallback

class CharactersUseCaseImpl : CharactersUseCase, CharacterListFromDataToDomainCallback {

    private var charactersRepository: CharactersRepository? = null
    private var callbackFromDomainToUi: CharacterListFromDomainToUiCallback? = null

    init {
        charactersRepository = CharactersRepositoryImpl()
        charactersRepository?.registerFromDataToDomainCallback(this@CharactersUseCaseImpl)
    }

    override fun registerFromDomainToUiCallback(callback: CharacterListFromDomainToUiCallback) {
        this.callbackFromDomainToUi = callback
    }

    override fun loadAllCharacters() {
        charactersRepository?.loadAllCharacters()
    }

    override fun getAllCharacters(charactersList: List<SingleCharacterDomain>) {
        callbackFromDomainToUi?.getAllCharacters(charactersList)
    }
}
package com.example.rickandmorty.domain.character.list

import com.example.rickandmorty.data.characters.list.CharactersRepositoryImpl
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.CharacterListFromDomainToUiCallback

class CharactersUseCaseImpl : CharactersUseCase, CharacterListFromDataToDomainCallback {

    private val charactersRepository: CharactersRepository by lazy {
        CharactersRepositoryImpl()
    }
    private var callbackFromDomainToUi: CharacterListFromDomainToUiCallback? = null

    /**
     * 2 шаг регистрируем коллбэк с помощью которого мы будем получать данные из дата слоя
     *  сюда в Домейн слой
     */
    init {
        charactersRepository.registerFromDataToDomainCallback(this@CharactersUseCaseImpl)
    }

    /**
     * 4 шаг регистрация коллбэка, для получения данных обратно, из домейн слоя в UI
     */
    override fun registerFromDomainToUiCallback(callback: CharacterListFromDomainToUiCallback) {
        this.callbackFromDomainToUi = callback
    }

    /**
     * 6 шаг
     * реализация запроса в сеть
     */
    override fun loadAllCharacters() {
        charactersRepository.loadAllCharacters()
    }

    /**
     * 10 шаг, данные пришли из Дата в Домейн и сразу же передались в Презентейш
     */
    override fun getAllCharactersFromDataToDomain(charactersList: List<SingleCharacterDomain>) {
        callbackFromDomainToUi?.getAllCharactersFromDomainToUi(charactersList)
    }
}
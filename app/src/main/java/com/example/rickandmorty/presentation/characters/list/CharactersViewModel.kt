package com.example.rickandmorty.presentation.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.character.list.CharactersUseCase
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.mapper.SingleCharacterDomainToSingleCharacterUiMapper
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val mapperFromDomainToUi: SingleCharacterDomainToSingleCharacterUiMapper
) : ViewModel(), CharacterListFromDomainToUiCallback {

    private var characters = MutableLiveData<List<SingleCharacterUi>>(emptyList())

    /**
     * 1 шаг
     * инициализируем Юс кейс, и как только его проинитили, сразу же  региструем обратный коллбэк
     * Юс кейс для того чтобы  сделать  запрос в сеть
     * Коллбэк для того, чтобы когда данные из сети придут. мы их получим в этот коллбэк
     * Регистрируем коллбэк с помощью Юс кейса
     */
    init {
        charactersUseCase.registerFromDomainToUiCallback(this@CharactersViewModel)
        loadAllCharacters()
    }

    /**
     * 11 шаг
     * Метод получения данных из коллбэка, т.е. как толлько данные обновяться со стороные запроса
     * то колбэк это услышит и заберет эти данные сюда
     */
    override fun getAllCharactersFromDomainToUi(characterList: List<SingleCharacterDomain>) {
        characters.postValue(
            mapperFromDomainToUi.map(characterList)
        )
    }

    /**
     *
     *  2, 13 шаг
     *  Метод который слушает наша активити или фрагмент, если в characters данные обновятся, то
     *  активити или фрагмент это услышит, и обновит их у себя
     */
    fun getAllCharacters(): LiveData<List<SingleCharacterUi>> = characters

    /**
     * 5 шаг
     * делаем запрос в сеть
     * Метод которые делает запрос в сеть
     */
    fun loadAllCharacters() {
        charactersUseCase.loadAllCharacters()
    }
}
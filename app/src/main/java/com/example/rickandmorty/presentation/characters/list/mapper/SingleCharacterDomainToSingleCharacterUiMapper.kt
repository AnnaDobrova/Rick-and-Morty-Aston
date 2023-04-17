package com.example.rickandmorty.presentation.characters.list.mapper

import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

/**
 * Преобразует данные из 1 вида датакласса в другой, необходимый нам
 */
class SingleCharacterDomainToSingleCharacterUiMapper {

    /**
     * 12 шаг маппинг данных из Домейна в UI
     */
    fun map(from: List<SingleCharacterDomain>): List<SingleCharacterUi> {
        return from.map {
            SingleCharacterUi(
                id = it.id.toInt(),
                image = it.image,
                name = it.name,
                species = it.species,
                gender = it.gender,
                status = it.status,
            )
        }
    }
}
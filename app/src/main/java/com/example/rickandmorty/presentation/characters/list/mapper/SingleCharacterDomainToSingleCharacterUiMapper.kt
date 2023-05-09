package com.example.rickandmorty.presentation.characters.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.domain.character.list.model.SingleCharacterDomain
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi

class SingleCharacterDomainToSingleCharacterUiMapper {

    fun map(from: PagingData<SingleCharacterDomain>) = from.map {
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
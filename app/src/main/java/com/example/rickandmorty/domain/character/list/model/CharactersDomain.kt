package com.example.rickandmorty.domain.character.list.model

import java.io.Serializable

data class CharactersDomain(
    val info: SingleCharacterInfoDomain,
    val characters: List<SingleCharacterDomain>
) : Serializable
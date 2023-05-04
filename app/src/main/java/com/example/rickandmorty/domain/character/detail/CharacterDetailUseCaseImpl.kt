package com.example.rickandmorty.domain.character.detail

import com.example.rickandmorty.domain.character.detail.model.CharacterDetailDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailsRepository: CharacterDetailRepository
) : CharacterDetailUseCase {

    override fun loadCharacterById(id: Int): Flow<AnnaResponse<CharacterDetailDomain>> {
        return characterDetailsRepository.loadCharacterById(id)
    }

}
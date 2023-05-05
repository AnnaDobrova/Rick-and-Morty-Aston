package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface LocationDetailUseCase {
    fun loadLocationById(id: Int): Flow<AnnaResponse<LocationDetailsDomain>>
}
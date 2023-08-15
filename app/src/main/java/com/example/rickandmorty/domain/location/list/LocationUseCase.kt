package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface LocationUseCase {
    fun getAllLocations(): Flow<AnnaResponse<List<SingleLocationDomain>>>
    fun getAllLocationFromLocal(): Flow<AnnaResponse<List<SingleLocationDomain>>>
}
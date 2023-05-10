package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse

interface LocationUseCase {
    suspend fun getAllLocations(): AnnaResponse<List<SingleLocationDomain>>
    suspend fun getAllLocationFromLocal(): AnnaResponse<List<SingleLocationDomain>>
}
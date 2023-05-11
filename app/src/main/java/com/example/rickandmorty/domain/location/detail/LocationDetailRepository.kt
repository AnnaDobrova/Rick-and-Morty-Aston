package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface LocationDetailRepository {
     fun loadLocationsById(id: Int): Flow<AnnaResponse<LocationDetailsDomain>>
     fun loadLocationsByIdFromLocal(id: Int): Flow<AnnaResponse<LocationDetailsDomain>>
}
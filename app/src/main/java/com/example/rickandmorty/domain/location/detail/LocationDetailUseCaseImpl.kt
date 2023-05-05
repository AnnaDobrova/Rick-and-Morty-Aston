package com.example.rickandmorty.domain.location.detail

import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationDetailUseCaseImpl @Inject constructor(
    private val locationDetailRepository: LocationDetailRepository
) : LocationDetailUseCase {

    override fun loadLocationById(id: Int): Flow<AnnaResponse<LocationDetailsDomain>> {
        return locationDetailRepository.loadLocationsById(id)
    }

}
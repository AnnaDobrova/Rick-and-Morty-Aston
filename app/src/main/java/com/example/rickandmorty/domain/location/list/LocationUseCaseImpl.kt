package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationUseCase {

    override suspend fun getAllLocations(): AnnaResponse<List<SingleLocationDomain>> {
        return locationRepository.getLocationList()
    }

    override suspend fun getAllLocationFromLocal(): AnnaResponse<List<SingleLocationDomain>> {
        return locationRepository.getLocationListFromLocal()
    }

}
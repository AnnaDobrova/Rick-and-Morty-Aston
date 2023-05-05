package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationUseCase {

    override suspend fun getAllLocations(): List<SingleLocationDomain> {
        return locationRepository.getLocationList()
    }

}
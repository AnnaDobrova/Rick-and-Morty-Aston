package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationUseCase {

    override fun getAllLocations(): Flow<AnnaResponse<List<SingleLocationDomain>>> {
        return locationRepository.getLocationList()
    }

    override fun getAllLocationFromLocal(): Flow<AnnaResponse<List<SingleLocationDomain>>> {
        return locationRepository.getLocationListFromLocal()
    }
}
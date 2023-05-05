package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.locations.list.api.LocationNetworkDataSours
import com.example.rickandmorty.data.locations.list.mapper.LocationListDataToLocationListDomainMapper
import com.example.rickandmorty.domain.location.list.LocationRepository
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private var locationsNetworkDataSource: LocationNetworkDataSours,
    private val mapperFromDataToDomain: LocationListDataToLocationListDomainMapper
) : LocationRepository {

    override suspend fun getLocationList(): List<SingleLocationDomain> {
        return mapperFromDataToDomain.map(
            locationsNetworkDataSource.getAllLocation().body()?.locations ?: emptyList()
        )
    }
}
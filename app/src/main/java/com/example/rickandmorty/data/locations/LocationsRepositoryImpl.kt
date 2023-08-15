package com.example.rickandmorty.data.locations

import com.example.rickandmorty.data.local.list.locations.LocationLocalDao
import com.example.rickandmorty.data.locations.list.api.LocationNetworkDataSours
import com.example.rickandmorty.data.locations.list.mapper.LocationListDataToLocationListDomainMapper
import com.example.rickandmorty.domain.location.list.LocationRepository
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private var locationsNetworkDataSource: LocationNetworkDataSours,
    private val mapperFromDataToDomain: LocationListDataToLocationListDomainMapper,
    private val locationLocalDao: LocationLocalDao
) : LocationRepository {

    override fun getLocationList(): Flow<AnnaResponse<List<SingleLocationDomain>>> {
        return flow {
            emit(
                try {
                    val response = locationsNetworkDataSource.getAllLocation()
                    locationLocalDao.setLocationListData(response.body()?.locations ?: emptyList())
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            locationsNetworkDataSource.getAllLocation().body()?.locations
                                ?: emptyList()
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }

    override fun getLocationListFromLocal(): Flow<AnnaResponse<List<SingleLocationDomain>>> {
        return flow {
            emit(
                try {
                    val response = locationLocalDao.getLocationListData()
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(response)
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }
}
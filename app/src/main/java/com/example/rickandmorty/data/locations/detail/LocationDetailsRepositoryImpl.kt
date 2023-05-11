package com.example.rickandmorty.data.locations.detail

import com.example.rickandmorty.data.local.detail.location.LocationDetailDao
import com.example.rickandmorty.data.locations.detail.api.LocationDetailsNetworkDataSource
import com.example.rickandmorty.data.locations.detail.mapper.LocationDetailDataToEpisodeDetailDomainMapper
import com.example.rickandmorty.domain.location.detail.LocationDetailRepository
import com.example.rickandmorty.domain.location.detail.model.LocationDetailsDomain
import com.example.rickandmorty.utils.AnnaResponse
import com.example.rickandmorty.utils.tryMapSuspended
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationDetailsRepositoryImpl @Inject constructor(
    private var locationDetailsNetworkDataSource: LocationDetailsNetworkDataSource,
    private val mapperFromDataToDomain: LocationDetailDataToEpisodeDetailDomainMapper,
    private val locationDetailDao: LocationDetailDao
) : LocationDetailRepository {

    override fun loadLocationsById(id: Int): Flow<AnnaResponse<LocationDetailsDomain>> {
        return flow {
            emit(
                try {
                    val response = locationDetailsNetworkDataSource.getLocationDetails(id)
                    locationDetailDao.deleteLocationDetail()

                    locationDetailDao.setSingleLocationDetail(response.body()!!)
                    AnnaResponse.Success(mapperFromDataToDomain.map(response.body()!!))
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }

    override fun loadLocationsByIdFromLocal(id: Int): Flow<AnnaResponse<LocationDetailsDomain>> {
        return flow {
            emit(
                try {
                    AnnaResponse.Success(
                        mapperFromDataToDomain.map(
                            locationDetailDao.getSingleLocationDetail(id)
                        )
                    )
                } catch (e: Throwable) {
                    AnnaResponse.Failure(e)
                }
            )
        }
    }
}
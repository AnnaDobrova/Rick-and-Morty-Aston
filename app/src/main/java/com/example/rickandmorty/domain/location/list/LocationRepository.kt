package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationList(): Flow<AnnaResponse<List<SingleLocationDomain>>>
    fun getLocationListFromLocal(): Flow<AnnaResponse<List<SingleLocationDomain>>>
}
package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.data.locations.list.model.LocationsData
import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain

interface LocationRepository {
   suspend fun getLocationList(): List<SingleLocationDomain>
}
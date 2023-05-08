package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain
import com.example.rickandmorty.utils.AnnaResponse

interface LocationRepository {
   suspend fun getLocationList(): AnnaResponse<List<SingleLocationDomain>>
}
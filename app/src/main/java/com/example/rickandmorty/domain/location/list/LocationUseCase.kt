package com.example.rickandmorty.domain.location.list

import com.example.rickandmorty.domain.location.list.model.SingleLocationDomain

interface LocationUseCase {
   suspend fun getAllLocations() : List<SingleLocationDomain>
}
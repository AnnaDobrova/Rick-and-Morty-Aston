package com.example.rickandmorty.domain.location.list

interface LocationRepository {
    fun registerFromDataToDomainCallback(callback: LocationListFromDataToDomainCallback)
    fun loadLocation()
}
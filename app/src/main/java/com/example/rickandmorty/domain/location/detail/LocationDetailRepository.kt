package com.example.rickandmorty.domain.location.detail

interface LocationDetailRepository {
    fun registerFromDataToDomainCallback(callback: LocationDetailFromDataToDomainCallback)
    fun loadLocations(id: Int)
}
package com.example.rickandmorty.presentation.locations

import com.example.rickandmorty.data.locations.detail.model.LocationDetailData

interface LocationDetailsListener {

    fun getLocationById(location: LocationDetailData)
}
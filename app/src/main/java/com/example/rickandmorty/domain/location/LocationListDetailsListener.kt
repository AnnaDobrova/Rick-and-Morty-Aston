package com.example.rickandmorty.domain.location

import com.example.rickandmorty.domain.location.list.Location

interface LocationListDetailsListener {

    fun goToDetailsLocation(location: Location)
}
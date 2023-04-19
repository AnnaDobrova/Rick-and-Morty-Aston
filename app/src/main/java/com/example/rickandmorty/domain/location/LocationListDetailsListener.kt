package com.example.rickandmorty.domain.location

import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI

interface LocationListDetailsListener {

    fun goToDetailsLocation(location: SingleLocationUI)
}
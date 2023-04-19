package com.example.rickandmorty.domain.location.detail.model

import java.io.Serializable

data class LocationDetailsDomain(
    val id: Long,
    val nameLocation: String,
    val typeLocation: String,
    val dimensionLocation: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : Serializable
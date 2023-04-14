package com.example.rickandmorty.domain.location.detail

data class LocationDetails (
    val id: Int,
    val nameLocation: String,
    val typeLocation : String,
    val dimensionLocation: String,
    val residents: List<String>,
    val url: String,
    val created: String
    )
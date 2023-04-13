package com.example.rickandmorty.domain.character.detail

data class CharacterDetail(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val location: LocationDetail,
    val episodeList: List<String>,
) {
    data class LocationDetail(
        val name: String,
        val url: String
    )
}
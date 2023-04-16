package com.example.rickandmorty.presentation.characters.detail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailUi(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val location: CharacterDetailLocationUi,
    val episodeList: List<String>,
) : Parcelable
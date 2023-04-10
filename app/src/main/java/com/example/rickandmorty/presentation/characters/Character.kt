package com.example.rickandmorty.presentation.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String
) : Parcelable
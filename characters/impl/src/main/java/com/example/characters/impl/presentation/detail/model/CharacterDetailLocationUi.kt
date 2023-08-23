package com.example.rickandmorty.presentation.characters.detail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailLocationUi(
    val name: String,
    val url: String
):Parcelable
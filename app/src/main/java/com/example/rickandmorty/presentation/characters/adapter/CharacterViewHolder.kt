package com.example.rickandmorty.presentation.characters.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rickandmorty.databinding.CharacterBinding
import com.example.rickandmorty.presentation.characters.Character

class CharacterViewHolder(
    character: View
) : RecyclerView.ViewHolder(character) {

    private val binding: CharacterBinding = CharacterBinding.bind(character)

    fun bindCharacter(
        character: Character
    ) {
        with(binding) {
            nameCharacter.text = character.name
            speciesCharacter.text = character.species
            genderCharacter.text = character.gender
            statusCharacter.text = character.status
        }
        loadImage(character)
    }

    private fun loadImage(character: Character) {
        Glide.with(itemView.context)
            .load(character.image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageCharacter)
    }
}

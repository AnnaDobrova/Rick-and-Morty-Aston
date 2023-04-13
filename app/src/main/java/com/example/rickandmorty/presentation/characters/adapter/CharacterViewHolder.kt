package com.example.rickandmorty.presentation.characters.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rickandmorty.databinding.CharacterBinding
import com.example.rickandmorty.domain.character.list.Character
import com.example.rickandmorty.domain.character.CharactersDetailsListener

class CharacterViewHolder(
    itemView: View,
   private val charactersDetailsListener: CharactersDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: CharacterBinding = CharacterBinding.bind(itemView)

    fun bindCharacter(
        character: Character,
    ) {
        with(binding) {
            idCharacter.text = character.id.toString()
            nameCharacter.text = character.name
            speciesCharacter.text = character.species
            genderCharacter.text = character.gender
            statusCharacter.text = character.status
        }
        loadImage(character)
        itemView.setOnClickListener{
            charactersDetailsListener.goToDetails(character)
        }
    }

    private fun loadImage(character: Character) {
        Glide.with(itemView.context)
            .load(character.image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageCharacter)
    }
}

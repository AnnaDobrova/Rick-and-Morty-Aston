package com.example.rickandmorty.presentation.episodes.details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterBinding
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi

class EpisodeDetailCharacterViewHolder(
    itemView: View,
    private val characterListInEpisode: CharacterListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    val binding = CharacterBinding.bind(itemView)

    fun bindCharacters(character: CharacterDetailUi) {
        binding.nameCharacter.text = character.name
        binding.speciesCharacter.text = character.species
        binding.genderCharacter.text = character.gender
        binding.statusCharacter.text = character.status

        itemView.setOnClickListener {
            characterListInEpisode.goToDetailsCharacter(character)
        }
    }
}
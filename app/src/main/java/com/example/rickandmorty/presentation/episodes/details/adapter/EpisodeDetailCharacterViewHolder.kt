package com.example.rickandmorty.presentation.episodes.details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterBinding
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener

class EpisodeDetailCharacterViewHolder(
    itemView: View,
    private val characterListInEpisode: CharacterListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    val binding = CharacterBinding.bind(itemView)

    fun bindLocationString(characterString: String) {
        binding.nameCharacter.text = characterString

        itemView.setOnClickListener {
            characterListInEpisode.getCharacterString(characterString)
        }
    }
}
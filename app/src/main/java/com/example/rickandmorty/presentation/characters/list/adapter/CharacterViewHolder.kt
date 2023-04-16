package com.example.rickandmorty.presentation.characters.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rickandmorty.databinding.CharacterBinding
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener

class CharacterViewHolder(
    itemView: View,
   private val characterListDetailsListener: CharacterListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: CharacterBinding = CharacterBinding.bind(itemView)

    fun bindCharacter(
        singleCharacterUi: SingleCharacterUi
    ) {
        with(binding) {
            idCharacter.text = singleCharacterUi.id.toString()
            nameCharacter.text = singleCharacterUi.name
            speciesCharacter.text = singleCharacterUi.species
            genderCharacter.text = singleCharacterUi.gender
            statusCharacter.text = singleCharacterUi.status
        }
        loadImage(singleCharacterUi)
        itemView.setOnClickListener{
            characterListDetailsListener.goToDetailsCharacter(singleCharacterUi)
        }
    }

    private fun loadImage(singleCharacterUi: SingleCharacterUi) {
        Glide.with(itemView.context)
            .load(singleCharacterUi.image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageCharacter)
    }
}

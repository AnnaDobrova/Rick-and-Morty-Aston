package com.example.rickandmorty.presentation.locations.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi

class CharacterListInLocationAdapter(private val characterListDetailsListener: CharacterListDetailsListener) :
    RecyclerView.Adapter<EpisodeDetailCharacterViewHolder>() {

    private var characterString = mutableListOf<CharacterDetailUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeDetailCharacterViewHolder {
        return EpisodeDetailCharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character,
                parent,
                false
            ),
            characterListDetailsListener
        )
    }

    override fun onBindViewHolder(holder: EpisodeDetailCharacterViewHolder, position: Int) {
        val location = characterString[position]
        holder.bindCharacter(location)
    }

    override fun getItemCount(): Int {
        return characterString.size
    }

    fun updateCharacterList(newCharacterList: List<CharacterDetailUi>) {
        characterString.clear()
        characterString.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}
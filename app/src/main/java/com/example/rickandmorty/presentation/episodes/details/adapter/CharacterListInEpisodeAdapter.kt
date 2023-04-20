package com.example.rickandmorty.presentation.episodes.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener

class CharacterListInEpisodeAdapter(private val characterListDetailsListener: CharacterListDetailsListener) :
    RecyclerView.Adapter<EpisodeDetailCharacterViewHolder>() {

    private var characterString = mutableListOf<String>()

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
        holder.bindLocationString(location)
    }

    override fun getItemCount(): Int {
        return characterString.size
    }

    fun updateCharacterListString(newCharacterList: List<String>) {
        characterString.clear()
        characterString.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}
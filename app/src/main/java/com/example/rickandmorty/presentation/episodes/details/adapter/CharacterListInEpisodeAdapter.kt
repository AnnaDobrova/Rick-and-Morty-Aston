package com.example.rickandmorty.presentation.episodes.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi

class CharacterListInEpisodeAdapter(private val characterListDetailsListener: CharacterListDetailsListener) :
    RecyclerView.Adapter<EpisodeDetailCharacterViewHolder>() {

    private var characterList = mutableListOf<CharacterDetailUi>()

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
        val episode = characterList[position]
        holder.bindCharacters(episode)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newEpisodeList: List<CharacterDetailUi>) {
        characterList.clear()
        characterList.addAll(newEpisodeList)
        notifyDataSetChanged()
    }

    fun addEpisode(episode: CharacterDetailUi) {
        characterList.add(episode)
        notifyDataSetChanged()
    }
}
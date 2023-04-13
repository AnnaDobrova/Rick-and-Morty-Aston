package com.example.rickandmorty.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.domain.character.CharactersDetailsListener

class CharacterAdapter(private val charactersDetailsListener: CharactersDetailsListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters = mutableListOf<Character>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character,
                parent,
                false
            ),
            charactersDetailsListener
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bindCharacter(character)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun updateListCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

}
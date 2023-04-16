package com.example.rickandmorty.presentation.characters.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener

class CharactersAdapter(private val characterListDetailsListener: CharacterListDetailsListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private var singleCharacterUis = mutableListOf<SingleCharacterUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character,
                parent,
                false
            ),
            characterListDetailsListener
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = singleCharacterUis[position]
        holder.bindCharacter(character)
    }

    override fun getItemCount(): Int {
        return singleCharacterUis.size
    }

    fun updateListCharacters(newSingleCharacterUis: List<SingleCharacterUi>) {
        singleCharacterUis.clear()
        singleCharacterUis.addAll(newSingleCharacterUis)
        notifyDataSetChanged()
    }

}
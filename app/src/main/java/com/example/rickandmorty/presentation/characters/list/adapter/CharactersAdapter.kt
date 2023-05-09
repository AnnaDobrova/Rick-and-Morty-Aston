package com.example.rickandmorty.presentation.characters.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener

class CharactersAdapter(
    private val characterListDetailsListener: CharacterListDetailsListener
) : PagingDataAdapter<SingleCharacterUi, CharacterViewHolder>(diffCallback) {

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
        getItem(position)?.let { holder.bindCharacter(it) }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SingleCharacterUi>() {
            override fun areItemsTheSame(oldItem: SingleCharacterUi, newItem: SingleCharacterUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SingleCharacterUi, newItem: SingleCharacterUi): Boolean {
                return oldItem == newItem
            }
        }
    }
}
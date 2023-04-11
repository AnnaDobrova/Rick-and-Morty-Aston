package com.example.rickandmorty.presentation.episodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.episodes.Episode

class EpisodeAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {

    private var episodes = mutableListOf<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episode,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bindEpisode(episode)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    fun updateEpisodes(newEpisode: List<Episode>){
        episodes.clear()
        episodes.addAll(newEpisode)
        notifyDataSetChanged()
    }
}
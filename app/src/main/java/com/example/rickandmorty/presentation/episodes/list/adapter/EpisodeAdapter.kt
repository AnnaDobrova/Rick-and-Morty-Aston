package com.example.rickandmorty.presentation.episodes.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI

class EpisodeAdapter(val episodeListDetailsListener: EpisodeListDetailsListener) :
    RecyclerView.Adapter<EpisodeViewHolder>() {

    private var episodes = mutableListOf<SingleEpisodeUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episode,
                parent,
                false
            ),
            episodeListDetailsListener
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bindEpisode(episode)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    fun updateEpisodes(newEpisode: List<SingleEpisodeUI>) {
        episodes.clear()
        episodes.addAll(newEpisode)
        notifyDataSetChanged()
    }
}
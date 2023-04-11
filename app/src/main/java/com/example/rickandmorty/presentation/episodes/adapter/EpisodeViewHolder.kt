package com.example.rickandmorty.presentation.episodes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodeBinding
import com.example.rickandmorty.presentation.episodes.Episode

class EpisodeViewHolder(
    episode: View
) : RecyclerView.ViewHolder(episode) {

    private val binding: EpisodeBinding = EpisodeBinding.bind(episode)

    fun bindEpisode(
        episode: Episode
    ) {
        with(binding){
            idEpisode.text = episode.id.toString()
            nameEpisode.text = episode.nameEpisode
            numberEpisode.text = episode.numberEpisode
            airDataEpisode.text = episode.airDataEpisode
        }
    }
}
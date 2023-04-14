package com.example.rickandmorty.presentation.episodes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodeBinding
import com.example.rickandmorty.domain.episode.list.Episode
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener

class EpisodeViewHolder(
    itemView: View,
    private val episodeListDetailsListener: EpisodeListDetailsListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: EpisodeBinding = EpisodeBinding.bind(itemView)

    fun bindEpisode(
        episode: Episode
    ) {
        with(binding){
            idEpisode.text = episode.id.toString()
            nameEpisode.text = episode.nameEpisode
            numberEpisode.text = episode.numberEpisode
            airDataEpisode.text = episode.airDataEpisode
        }
        itemView.setOnClickListener {
            episodeListDetailsListener.goToDetailsEpisode(episode)
        }
    }
}